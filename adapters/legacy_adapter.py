# STUDENT SKELETON: adapters/legacy_adapter.py
import math
from core.target_interface import ShippingRateProvider
from core.models import ShippingQuote
from core.exceptions import ShippingServiceException
from vendors.legacy_postal import OldPostalService, OldPostalSocketTimeout


class OldPostalAdapter(ShippingRateProvider):
    """
    Object Adapter wrapping OldPostalService via composition.
    """

    def __init__(self, service: OldPostalService):
        # TODO: Store the adaptee instance in a private attribute (self._service)
        raise NotImplementedError("Implement __init__ using composition")

    def get_quote(self, weight_kg: float, destination_zip: str) -> ShippingQuote:
        """
        TASK 1 REQUIREMENTS:
        1. Parse destination_zip to int. If it fails, raise ShippingServiceException.
        2. Convert weight_kg to ounces: weight_oz = weight_kg * 35.274
        3. Call self._service.compute_fare(weight_oz, zip_int) inside a try/except block.
        4. Catch OldPostalSocketTimeout or ValueError and re-raise ShippingServiceException.
        5. Unpack fee_cents -> cost_usd (fee_cents / 100.0, rounded to 2 decimals).
        6. Unpack transit_hours -> delivery_days: math.ceil(transit_hours / 24.0) (min 1).
        7. Return ShippingQuote(cost_usd, delivery_days, carrier_name='OldPostalService').
        """
        raise NotImplementedError("Implement get_quote")
