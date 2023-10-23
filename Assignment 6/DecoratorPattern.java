// Component interface representing the core WebPage
interface WebPage {
    void display();
}

// Concrete component representing a basic web page
class BasicWebPage implements WebPage {
    @Override
    public void display() {
        System.out.println("Displaying a basic web page.");
    }
}

// Decorator class for adding authorization check
class AuthorizationDecorator implements WebPage {
    private WebPage page;

    public AuthorizationDecorator(WebPage page) {
        this.page = page;
    }

    @Override
    public void display() {
        if (isAuthorized()) {
            page.display();
        } else {
            System.out.println("Access denied. Please log in.");
        }
    }

    private boolean isAuthorized() {
        // Simulate an authorization check (e.g., by checking user credentials)
        return true; // For simplicity, always return true
    }
}

// Decorator class for adding pagination
class PaginationDecorator implements WebPage {
    private WebPage page;
    private int currentPage;
    private int pageSize;

    public PaginationDecorator(WebPage page, int currentPage, int pageSize) {
        this.page = page;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    @Override
    public void display() {
        // Simulate pagination logic here
        System.out.println("Displaying Page " + currentPage + " of " + pageSize);
        page.display();
    }
}

public class DecoratorPattern{
    public static void main(String[] args) {
        // Create a basic web page
        WebPage basicWebPage = new BasicWebPage();

        // Add authorization and pagination decorators
        WebPage authorizedWebPage = new AuthorizationDecorator(basicWebPage);
        WebPage paginatedWebPage = new PaginationDecorator(authorizedWebPage, 1, 5);

        // Display the web page with decorators
        paginatedWebPage.display();
    }
}
