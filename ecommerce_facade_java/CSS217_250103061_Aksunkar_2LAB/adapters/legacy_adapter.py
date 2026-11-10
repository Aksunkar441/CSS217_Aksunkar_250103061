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
        self._service = service

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
        try:
            zip_int = int(destination_zip)
        except ValueError as e:
            raise ShippingServiceException(
                f"Invalid ZIP code for legacy postal service: {destination_zip}"
            ) from e

        weight_oz = weight_kg * 35.274

        try:
            response = self._service.compute_fare(weight_oz, zip_int)
        except (OldPostalSocketTimeout, ValueError) as e:
            raise ShippingServiceException(f"Legacy postal error: {e}") from e

        fee_cents = response["data"]["fee_cents"]
        transit_hours = response["data"]["transit_hours"]

        cost_usd = round(fee_cents / 100.0, 2)
        delivery_days = max(1, math.ceil(transit_hours / 24.0))

        return ShippingQuote(
            cost_usd=cost_usd,
            delivery_days=delivery_days,
            carrier_name="OldPostalService",
        )
