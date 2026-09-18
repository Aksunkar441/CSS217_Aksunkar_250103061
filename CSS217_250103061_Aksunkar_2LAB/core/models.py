from dataclasses import dataclass


@dataclass(frozen=True)
class ShippingQuote:
    """Unified domain model returned to all SwiftShip clients."""
    cost_usd: float
    delivery_days: int
    carrier_name: str
