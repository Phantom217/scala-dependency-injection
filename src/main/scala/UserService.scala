// using self-type annotation declaring the dependencies this
// component requires, in our case the `UserRepositoryComponent`
trait UserServiceComponent { this: UserRepositoryComponent =>
  val userService = new UserService
  class UserService {
    def authenticate(username: String, password: String): User =
      userRepository.authenticate(username, password)

    def create(username: String, password: String) =
      userRepository.create(new User(username, password))

    def delete(user: User) = userRepository.delete(user)
  }
}
