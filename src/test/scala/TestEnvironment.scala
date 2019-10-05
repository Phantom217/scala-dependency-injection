trait TestEnvironment extends
  UserServiceComponent with
  UserRepositoryComponent with
  org.specs.mock.JMocker
{
  val userRepository = mock(classOf[UserRepository])
  val userService = mock(classOf[UserService])
}
