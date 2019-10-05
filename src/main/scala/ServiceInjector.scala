// Using Google Guice
// technique based on a ServiceInjector mixin

// ==========================
// service interface
trait OnOffDevice {
  def on: Unit
  def off: Unit
}
trait SensorDevice {
  def isCoffeePresent: Boolean
}
trait IWarmer {
  def trigger
}
trait Client

// ==========================
// service implementations
class Heater extends OnOffDevice {
  def on = println("heater.on")
  def off = println("heater.off")
}
class PotSensor extends SensorDevice {
  def isCoffeePresent = true
}
class @Inject Warmer(
  val potSensor: SensorDevice,
  val heater: OnOffDevice)
  extends IWarmer {

  def trigger = {
    if (potSensor.isCoffeePresent) heater.on
    else heater.off
  }
}

// ==========================
// client
class @Inject Client(val warmer: Warmer) extends Client {
  warmer.trigger
}

// ==========================
// Guice's configuration class that is definint the
// interface-implementation bindings
class DependencyModule extends Module {
  def configure(binder: Binder) = {
    binder.bind(classOf[OnOffDevice]).to(classOf[Heater])
    binder.bind(classOf[SensorDevice]).to(classOf[PotSensor])
    binder.bind(classOf[IWarmer]).to(classOf[Warmer])
    binder.bind(classOf[Client]).to(classOf[MyClient])
  }
}

// ==========================
// Usage: val bean = new Bean with ServiceInjector
trait ServiceInjector {
  ServiceInjector.inject(this)
}

// helper companion object
object ServiceInjector {
  private val injector = Guice.createInjector(
    Array[Module](new DependencyModule))
  def inject(obj: AnyRef) = injector.injectMembers(obj)
}

// ==========================
// mix-in the ServiceInjector trait to perform injection
// upon instantiation
val client = new MyClient with ServiceInjector

println(client)
