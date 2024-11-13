package com.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

// Main Application Class
@SpringBootApplication
public class ChatbotApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatbotApplication.class, args);
    }
}

// Entities (Configuration, Interaction, Feedback)
@Entity
class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String responseTemplate;
    private String knowledgeBase;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getResponseTemplate() { return responseTemplate; }
    public void setResponseTemplate(String responseTemplate) { this.responseTemplate = responseTemplate; }
    public String getKnowledgeBase() { return knowledgeBase; }
    public void setKnowledgeBase(String knowledgeBase) { this.knowledgeBase = knowledgeBase; }
}

@Entity
class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String query;
    private String response;
    private LocalDateTime date = LocalDateTime.now();

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }
    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }
    public LocalDateTime getDate() { return date; }
}

@Entity
class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String feedbackText;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFeedbackText() { return feedbackText; }
    public void setFeedbackText(String feedbackText) { this.feedbackText = feedbackText; }
}

// Repositories
interface ConfigurationRepository extends JpaRepository<Configuration, Long> {}
interface InteractionRepository extends JpaRepository<Interaction, Long> {}
interface FeedbackRepository extends JpaRepository<Feedback, Long> {}

// Service Layer
@Service
class ChatbotService {
    @Autowired private ConfigurationRepository configRepo;
    @Autowired private InteractionRepository interactionRepo;
    @Autowired private FeedbackRepository feedbackRepo;

    public Configuration saveConfiguration(Configuration config) {
        return configRepo.save(config);
    }

    public List<Interaction> getAllInteractions() {
        return interactionRepo.findAll();
    }

    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepo.save(feedback);
    }
}

// Controllers
@RestController
@RequestMapping("/admin")
class AdminController {
    @Autowired private ChatbotService chatbotService;

    @PostMapping("/configure")
    public Configuration saveConfiguration(@RequestBody Configuration config) {
        return chatbotService.saveConfiguration(config);
    }

    @GetMapping("/interactions")
    public List<Interaction> getAllInteractions() {
        return chatbotService.getAllInteractions();
    }

    @PostMapping("/algorithm/update")
    public String updateAlgorithm() {
        // Placeholder for algorithm update functionality
        return "Algorithm updated successfully!";
    }
}

@RestController
@RequestMapping("/user")
class UserController {
    @Autowired private ChatbotService chatbotService;

    @GetMapping("/interactions")
    public List<Interaction> getUserInteractions() {
        return chatbotService.getAllInteractions();
    }

    @PostMapping("/feedback")
    public Feedback submitFeedback(@RequestBody Feedback feedback) {
        return chatbotService.saveFeedback(feedback);
    }
}
