package com.service;

import com.dao.ChatbotDAO;
import com.model.User;
import com.model.Conversation;
import com.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatbotService {

    private final ChatbotDAO chatbotDAO;
    private final DatabaseUtil databaseUtil;

    // Constructor to initialize ChatbotDAO with DatabaseUtil
    public ChatbotService(DatabaseUtil databaseUtil) {
        this.databaseUtil = databaseUtil;
        this.chatbotDAO = new ChatbotDAO(databaseUtil);
    }

    /**
     * Adds a new user to the database.
     *
     * @param username The username of the user.
     * @param email    The email of the user.
     * @throws SQLException If a database error occurs.
     */
    public void addUser (String username, String email) throws SQLException {
        User user = new User(username, email);
        chatbotDAO.addUser  (user);
    }

    /**
     * Improves the chatbot's algorithm based on feedback and performance data.
     *
     * @param feedback       The feedback provided by users or administrators.
     * @param performanceData The performance data of the chatbot.
     * @throws SQLException If a database error occurs.
     */
    public void improveAlgorithm(String feedback, String performanceData) throws SQLException {
        String sql = "INSERT INTO AlgorithmImprovement (feedback, performance_data) VALUES (?, ?)";
        try (Connection conn = databaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, feedback);
            pstmt.setString(2, performanceData);
            pstmt.executeUpdate();
        }
    }

    /**
     * Adds a new conversation to the database.
     *
     * @param userId   The ID of the user.
     * @param message  The message sent by the user.
     * @param response The response from the chatbot.
     * @throws SQLException If a database error occurs.
     */
    public void addConversation(int userId, String message, String response) throws SQLException {
        Conversation conversation = new Conversation(userId, message, response);
        chatbotDAO.addConversation(conversation);
    }

    /**
     * Retrieves the conversation history for a specific user.
     *
     * @param userId The ID of the user.
     * @return A ResultSet containing the conversation history.
     * @throws SQLException If a database error occurs.
     */
    public ResultSet getConversationHistory(int userId) throws SQLException {
        return chatbotDAO.getConversationHistory(userId);
    }

    public void updateConfiguration(String responseTemplate, String knowledgeBase) throws SQLException {
        String sql = "UPDATE Configuration SET response_template = ?, knowledge_base = ? WHERE id = 1";
        try (Connection conn = databaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, responseTemplate);
            pstmt.setString(2, knowledgeBase);
            pstmt.executeUpdate();
        }
    }

    public ResultSet getAnalytics() throws SQLException {
        String sql = "SELECT * FROM InteractionLogs";
        Connection conn = databaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        return pstmt.executeQuery();
    }

    /**
     * Prints the conversation history for a specific user.
     *
     * @param userId The ID of the user.
     * @throws SQLException If a database error occurs.
     */
    public void printConversationHistory(int userId) throws SQLException {
        ResultSet resultSet = chatbotDAO.getConversationHistory(userId);
        System.out.println("Conversation History for User ID: " + userId);
        while (resultSet.next()) {
            System.out.println("Message: " + resultSet.getString("message"));
            System.out.println("Response: " + resultSet.getString("response"));
            System.out.println("Timestamp: " + resultSet.getTimestamp("timestamp"));
            System.out.println("-----------------------------");
        }
    }
}