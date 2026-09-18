# IMMUTABLE AUTOMATED TEST SUITE: tests/test_shipping_engine.py
import pytest
from core.exceptions import ShippingServiceException
from core.models import ShippingQuote
from core.order_dispatcher import OrderDispatcher
from vendors.legacy_postal import OldPostalService, OldPostalSocketTimeout
from vendors.fast_freight import FastFreightCloud, CarrierHttpError
from adapters.legacy_adapter import OldPostalAdapter
from adapters.fast_freight_adapter import FastFreightAdapter


# --- LEGACY CARRIER TESTS (TASK 1) ---


def test_old_postal_standard_quote():
    service = OldPostalService()
    adapter = OldPostalAdapter(service)
    # 2.0 kg = 70.548 oz. Fare: 500 + int(70.548 * 25) = 500 + 1763 = 2263 cents -> $22.63
    # Transit: 48 + int(70.548 // 50)*12 = 48 + 12 = 60 hours -> ceil(60/24) = 3 days
    quote = adapter.get_quote(2.0, "90210")
    assert isinstance(quote, ShippingQuote)
    assert quote.carrier_name == "OldPostalService"
    assert quote.cost_usd == 22.63
    assert quote.delivery_days == 3


def test_old_postal_small_parcel():
    service = OldPostalService()
    adapter = OldPostalAdapter(service)
    # 0.5 kg = 17.637 oz. Fare: 500 + int(17.637 * 25) = 500 + 440 = 940 cents -> $9.40
    # Transit: 48 hours -> ceil(48/24) = 2 days
    quote = adapter.get_quote(0.5, "10001")
    assert quote.cost_usd == 9.40
    assert quote.delivery_days == 2


def test_old_postal_timeout_exception_isolation():
    service = OldPostalService()
    adapter = OldPostalAdapter(service)
    # ZIP 00000 triggers OldPostalSocketTimeout in the legacy hardware
    with pytest.raises(ShippingServiceException) as exc_info:
        adapter.get_quote(1.0, "00000")
    # Crucial: verify that the raw vendor socket error DID NOT leak
    assert not isinstance(exc_info.value, OldPostalSocketTimeout)


def test_old_postal_invalid_alphanumeric_zip():
    service = OldPostalService()
    adapter = OldPostalAdapter(service)
    with pytest.raises(ShippingServiceException):
        adapter.get_quote(1.0, "ABCDE")


# --- MODERN CLOUD CARRIER TESTS (TASK 2) ---


def test_fast_freight_standard_quote():
    service = FastFreightCloud()
    adapter = FastFreightAdapter(service)
    # 2.5 kg = 2500 grams. EUR: 4.50 + 2500 * 0.003 = 12.00 EUR
    # USD: round(12.00 * 1.08, 2) = $12.96
    quote = adapter.get_quote(2.5, "90210")
    assert isinstance(quote, ShippingQuote)
    assert quote.carrier_name == "FastFreightCloud"
    assert quote.cost_usd == 12.96
    assert quote.delivery_days == 2


def test_fast_freight_micro_parcel():
    service = FastFreightCloud()
    adapter = FastFreightAdapter(service)
    # 0.1 kg = 100 grams. EUR: 4.50 + 100 * 0.003 = 4.80 EUR
    # USD: round(4.80 * 1.08, 2) = $5.18
    quote = adapter.get_quote(0.1, "30301")
    assert quote.cost_usd == 5.18
    assert quote.delivery_days == 2


def test_fast_freight_http_error_isolation():
    service = FastFreightCloud()
    adapter = FastFreightAdapter(service)
    # Invalid postal code triggers HTTP 422 in cloud API
    with pytest.raises(ShippingServiceException) as exc_info:
        adapter.get_quote(1.0, "123")
    assert not isinstance(exc_info.value, CarrierHttpError)


def test_fast_freight_negative_weight_isolation():
    service = FastFreightCloud()
    adapter = FastFreightAdapter(service)
    with pytest.raises(ShippingServiceException):
        adapter.get_quote(-1.5, "90210")


# --- CLIENT DISPATCHER INTEGRATION & POLYMORPHISM (TASK 3) ---


def test_order_dispatcher_with_legacy_adapter():
    adapter = OldPostalAdapter(OldPostalService())
    dispatcher = OrderDispatcher(adapter)
    res = dispatcher.dispatch_order("ORD-101", 2.0, "90210")
    assert res["status"] == "DISPATCH_READY"
    assert res["carrier"] == "OldPostalService"
    assert res["shipping_fee"] == 22.63
    assert res["eta_days"] == 3


def test_order_dispatcher_with_fast_freight_adapter():
    adapter = FastFreightAdapter(FastFreightCloud())
    dispatcher = OrderDispatcher(adapter)
    res = dispatcher.dispatch_order("ORD-202", 2.5, "90210")
    assert res["status"] == "DISPATCH_READY"
    assert res["carrier"] == "FastFreightCloud"
    assert res["shipping_fee"] == 12.96
    assert res["eta_days"] == 2
