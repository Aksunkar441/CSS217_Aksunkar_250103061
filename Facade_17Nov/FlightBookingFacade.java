public class FlightBookingFacade {
    private final SeatInventorySystem seatInventorySystem;
    private final BaggagePricingEngine baggagePricingEngine;
    private final CurrencyConverter currencyConverter;
    private final PaymentProcessor paymentProcessor;
    private final TicketIssuer ticketIssuer;

    public FlightBookingFacade(SeatInventorySystem seatInventorySystem,
                               BaggagePricingEngine baggagePricingEngine,
                               CurrencyConverter currencyConverter,
                               PaymentProcessor paymentProcessor,
                               TicketIssuer ticketIssuer){


        this.seatInventorySystem = seatInventorySystem;
        this.baggagePricingEngine = baggagePricingEngine;
        this.currencyConverter = currencyConverter;
        this.paymentProcessor = paymentProcessor;
        this.ticketIssuer = ticketIssuer;
    }

    public String bookFlight(String passengerName,
                             String flightCode,
                             String seatNumber,
                             double baseFareUSD,
                             int bagCount,
                             double bagWeightKg,
                             String currency,
                             String cardNumber){
        String lockToken = null;

        try {
// TODO 1: Connect to seat server and lock the requested seat.
            seatInventorySystem.connectToReservationServer();
            lockToken = seatInventorySystem.lockSeat(flightCode, seatNumber,
                    passengerName);

// TODO 2: Compute baggage fee in USD and add to baseFareUSD.
            baseFareUSD = baggagePricingEngine.calculateBaggageFeeUSD(bagCount,
                    bagWeightKg);
            double totalUSD = 0;
            totalUSD += baseFareUSD;

// TODO 3: Convert total USD amount into target currency.
            double convertedTotal = currencyConverter.convertFromUSD(totalUSD, currency);
// TODO 4: Initialize payment gateway and charge the card.
            String receiptId = paymentProcessor.chargeCard(cardNumber, convertedTotal, currency);

// TODO 5: Generate and return the final e-ticket code.
            ticketIssuer.generateTicket(passengerName, flightCode, lockToken, receiptId);
            return receiptId;
        } catch (RuntimeException ex) {

// TODO 6: If a seat was already locked, release it before reporting failure.
            if (lockToken != null){
                seatInventorySystem.releaseSeatLock(lockToken);
            }
            return "BOOKING_FAILED: " + ex.getMessage();
        } finally {
// TODO 7: Ensure seat server disconnects regardless of success or failure.
            seatInventorySystem.disconnect();
        }
    }
}

