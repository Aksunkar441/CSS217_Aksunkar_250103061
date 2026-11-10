from abc import ABC, abstractmethod
from core.models import ShippingQuote


class ShippingRateProvider(ABC):
    """
    TARGET INTERFACE
    All carrier adapters must implement this contract.
    """

    @abstractmethod
    def get_quote(self, weight_kg: float, destination_zip: str) -> ShippingQuote:
        """
        Calculates shipping rate and estimated delivery days.
        :param weight_kg: Parcel weight in kilograms (float > 0.0)
        :param destination_zip: 5-digit destination postal code as a string
        :return: Standardized ShippingQuote instance
        :raises ShippingServiceException: On invalid input or carrier failure
        """
        pass
