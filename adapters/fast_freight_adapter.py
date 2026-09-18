# STUDENT SKELETON: adapters/fast_freight_adapter.py
from core.target_interface import ShippingRateProvider
from core.models import ShippingQuote
from core.exceptions import ShippingServiceException
from vendors.fast_freight import FastFreightCloud, CarrierPayload, CarrierHttpError


class FastFreightAdapter(ShippingRateProvider):
    """
    Object Adapter wrapping FastFreightCloud via composition.
    """

    EUR_TO_USD_EXCHANGE_RATE = 1.08

    def __init__(self, service: FastFreightCloud):
        # TODO: Store the adaptee instance in a private attribute (self._service)
        raise NotImplementedError("Implement __init__ using composition")

    def get_quote(self, weight_kg: float, destination_zip: str) -> ShippingQuote:
        """
        TASK 2 REQUIREMENTS:
        1. Convert weight_kg to integer grams: grams = int(weight_kg * 1000)
        2. Create CarrierPayload(weight_grams=grams, postal_code_str=str(destination_zip).strip())
        3. Call self._service.fetch_quote(payload) inside a try/except block.
        4. Catch CarrierHttpError and re-raise ShippingServiceException.
        5. Convert returned EUR to USD: cost_usd = round(cost_eur * self.EUR_TO_USD_EXCHANGE_RATE, 2)
        6. Delivery SLA is fixed at 2 days.
        7. Return ShippingQuote(cost_usd, delivery_days=2, carrier_name='FastFreightCloud').
        """
        raise NotImplementedError("Implement get_quote")
