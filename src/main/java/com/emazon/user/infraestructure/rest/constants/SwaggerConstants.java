package com.emazon.user.infraestructure.rest.constants;

public class SwaggerConstants {
    public static final String REGISTER_CLIENT_SUMMARY = "Register a New Client";
    public static final String REGISTER_CLIENT_DESCRIPTION = "This endpoint allows for the registration of a new client by providing necessary details including personal information, contact details, and associated roles. " +
            "Ensure that all required fields are completed accurately. A successful registration will result in a confirmation message being returned.";
    public static final String REGISTER_CLIENT_API_RESPONSES_201_DESCRIPTION = "The client was successfully registered. A confirmation message will be returned, including the details of the newly created client.";
    public static final String REGISTER_CLIENT_API_RESPONSES_400_DESCRIPTION = "The request could not be processed due to invalid input. Ensure all required fields are included and correctly formatted. Common issues include missing data or invalid format.";
    public static final String REGISTER_CLIENT_API_RESPONSES_409_DESCRIPTION = "A conflict occurred while processing the request. This error typically happens if a client with the same email or username already exists. Verify that the provided details are unique and try again.";


    public static final String REGISTER_WAREHOUSE_ASSISTANT_SUMMARY = "Register a New Warehouse Assistant";
    public static final String REGISTER_WAREHOUSE_ASSISTANT_DESCRIPTION = "This endpoint allows for the registration of a new warehouse assistant by providing all necessary details including personal information, contact details, and associated role. " +
            "Ensure that all required fields are accurately completed. A successful registration will return a confirmation message.";
    public static final String REGISTER_WAREHOUSE_ASSISTANT_API_RESPONSES_201_DESCRIPTION = "The warehouse assistant was successfully registered. A confirmation message will be returned, including the details of the newly created assistant.";
    public static final String REGISTER_WAREHOUSE_ASSISTANT_API_RESPONSES_400_DESCRIPTION = "The request could not be processed due to invalid input. Ensure all required fields are included and formatted correctly. Common issues include missing data or incorrect format.";
    public static final String REGISTER_WAREHOUSE_ASSISTANT_API_RESPONSES_409_DESCRIPTION = "A conflict occurred while processing the request. This error typically happens if a warehouse assistant with the same email or username already exists. Verify that the provided details are unique and try again.";
    public static final String CREATE_USER_WAREHOUSE_ASSISTANT_MESSAGE = "Warehouse assistant registered successfully";




    public static final String GET_USERINFO_SUMMARY = "Retrieve User Information";
    public static final String GET_USERINFO_DESCRIPTION = "Fetches user information based on the authentication context.";

    public static final String GET_USERINFO_DESCRIPTION_API_RESPONSES_200_DESCRIPTION = "User information successfully retrieved.";
    public static final String GET_USERINFO_DESCRIPTION_API_RESPONSES_403_DESCRIPTION = "Forbidden access, authentication required.";
    public static final String GET_USERINFO_DESCRIPTION_API_RESPONSES_404_DESCRIPTION = "User not found.";


    // Información general sobre la operación
    public static final String GET_CLIENTINFO_BYID_SUMMARY = "Retrieve Client Information by ID";
    public static final String GET_CLIENTINFO_BYID_DESCRIPTION = "Fetches client information based on the provided client ID.";

    // Descripciones para las respuestas
    public static final String GET_CLIENTINFO_DESCRIPTION_API_RESPONSES_200_DESCRIPTION = "Client information successfully retrieved.";
    public static final String GET_CLIENTINFO_DESCRIPTION_API_RESPONSES_404_DESCRIPTION = "Client not found with the provided ID.";




    //AUTHENTICATION
    public static final String AUTHENTICATE_USER_SUMMARY = "Authenticate User";
    public static final String AUTHENTICATE_USER_DESCRIPTION = "Authenticates a user based on the provided credentials and returns an authentication token.";

    // Descripciones para las respuestas
    public static final String AUTHENTICATE_USER_API_RESPONSES_200_DESCRIPTION = "Authentication successful, token issued.";
    public static final String AUTHENTICATE_USER_API_RESPONSES_404_DESCRIPTION = "User not found or credentials are incorrect.";
    public static final String AUTHENTICATE_USER_API_RESPONSES_401_DESCRIPTION = "Unauthorized, invalid credentials.";


}
