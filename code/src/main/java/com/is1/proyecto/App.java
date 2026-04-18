public static void main(String[] args) {
    port(8080);
    DBConfigSingleton dbConfig = DBConfigSingleton.getInstance();
    initializeDatabase(dbConfig);
    configureFilters(dbConfig);

    new UserRoutes().register();
    new ProfessorRoutes().register();
}