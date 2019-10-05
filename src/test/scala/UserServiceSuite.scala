class UserServiceSuite extends TestNGSuite with TestEnvironment {

  @Test { val groups=Array("unit") }
  def authenticateUser = {

    // create a fresh and clean (non-mock) `UserService`
    // (who's `userRepository` is still a mock)
    val userService = new UserService

    // record the mock invocation
    expect {
      val user = new User("test", "test")
      ine(userRepository).authenticate(user)
    }

    // ... // test the authentication method
  }
  // ...
}
