from core.target_interface import ShippingRateProvider


class OrderDispatcher:
    """
    CLIENT COMPONENT
    Relies purely on the ShippingRateProvider interface via Dependency Injection.
    Zero awareness of vendor-specific classes or quirks.
    """

    def __init__(self, provider: ShippingRateProvider):
        self._provider = provider

    def dispatch_order(self, order_id: str, weight_kg: float, destination_zip: str) -> dict:
        quote = self._provider.get_quote(weight_kg, destination_zip)
        return {
            "order_id": order_id,
            "carrier": quote.carrier_name,
            "shipping_fee": quote.cost_usd,
            "eta_days": quote.delivery_days,
            "status": "DISPATCH_READY",
        }
