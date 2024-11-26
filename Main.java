import config.Database;
import repositories.TodoListRepositoryImpl;
import repositories.TodoListRepository;
import services.TodoListService;
import services.TodoListServiceImpl;
import views.TodoListTerminalView;
import views.TodoListView; // Fixed typo


public class Main {
    public static void main(String[] args) {
        Database database = new Database("db_gafint", "root", "", "Localhost", "3306");
        database.setup();

        TodoListRepository todoListRepository = new TodoListRepositoryImpl(database);


        TodoListService todoListService = new TodoListServiceImpl(todoListRepository);


        TodoListView todoListView = (TodoListView) new TodoListTerminalView(todoListService);

        todoListView.run();
    }
}