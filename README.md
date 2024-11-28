# KotlinLastCrusade1

This project uses the GitHub API to retrieve and display information from Users and Repositories in a user-details screen. The goal is to implement Clean Architecture principles by organizing code into Repositories, Repository Interfaces, Mappers, and UseCases. Dependency injection is managed with Koin, and the project includes Unit Tests to ensure robustness.

## Technical Overview

- **Clean Architecture**: Code is organized in layers with clear responsibilities, separating business logic from presentation logic.
- **SOLID Principles**: Applying principles like single responsibility and dependency inversion to ensure the project’s scalability and maintainability.
- **Repository Pattern**: Repository interfaces for data access, promoting decoupling between layers.
- **Mappers**: Data transformation between different layers and models.
- **UseCases**: Encapsulation of business rules and core operations, isolating application logic.
- **Koin for Dependency Injection**: Simplified configuration of dependencies for modularity and testing.
- **Unit Testing**: Ensuring the functionality and integration between layers to prevent regressions and maintain quality.
- **LiveData**: For observing and updating the UI with reactive data.

## KotlinLastCrusade1 Wiki

For a deeper dive into the architecture, SOLID principles, and additional technical information used, please refer to the Wiki.

- [Wiki](https://github.com/Crusade4Code/crusade4code.github.io/wiki/KotlinLastCrusade1)

# Branches and Technologies Variations
                
<pre style="display: inline-block; margin: 0; padding: 0; background: none; border: none;">
              ┌──────────────┐        
              │              │        
              │     <a href="https://github.com/Crusade4Code/kotlinlastcrusade1-xml-koin-mapper-usecase/tree/main" style="text-decoration: none; color: inherit; display: inline-block;">Main</a>     │   LiveData   (YOU ARE HERE)
              │              │        
              └──────┬───────┘        
                     │                
       ┌─────────────┴───────────────
       │                              
┌──────┴───────┐                      
│              │                      
│     <a href="https://github.com/Crusade4Code/kotlinlastcrusade1-xml-koin-mapper-usecase/tree/flow" style="text-decoration: none; color: inherit; display: inline-block;">Flow</a>     │                      
│              │                      
└──────────────┘                      
</pre> 

## Getting Started
1. Clone the repository.
2. Configure your GitHub API token if required.
3. Run the project on Android Studio.

## License
This project is licensed under the **The Unlicense** - see the LICENSE file for details.
