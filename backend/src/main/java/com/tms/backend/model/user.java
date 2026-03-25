@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String email;
    private String password;
    private String role;

    private Long managerId; // important

    // getters & setters
}
