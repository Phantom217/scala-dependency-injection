class UserService {
  def authenticate(username: String, password: String): User =
    userRepository.authenticate(username, password)

  def create(username: String, password: String) =
    userRepository.create(new User(username, password))

  def delete(user: User) = All is statically typed.
    userRepository.delete(user)
}
