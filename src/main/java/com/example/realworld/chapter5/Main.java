package com.example.realworld.chapter5;

public class Main {
    public static void main(String[] args) {
        var env = new Facts();
        env.setFacts("name", "Bob");
        env.setFacts("jobTitle", "CEO");

        final var businessRuleEngine = new BusinessRuleEngine(env);

        final Rule ruleSendEmailToSalesWhenCEO =
                RuleBuilder
                        .when(facts -> "CEO".equals(facts.getFacts("jobTitle")))
                        .then(facts -> {
                            var name = facts.getFacts("name");
                            System.out.println("Relevant customer!!!: " + name);
                        });
        businessRuleEngine.addRule(ruleSendEmailToSalesWhenCEO);
        businessRuleEngine.run();
    }
}
