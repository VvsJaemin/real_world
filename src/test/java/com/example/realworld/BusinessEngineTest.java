package com.example.realworld;

import com.example.realworld.chapter5.Action;
import com.example.realworld.chapter5.BusinessRuleEngine;
import com.example.realworld.chapter5.Facts;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BusinessEngineTest {

    @Test
    void shouldHaveNoRulesInitially() {
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();

        assertEquals(0, businessRuleEngine.count());

    }

    @Test
    void shouldAddTwoActions() {
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();

        businessRuleEngine.addAction(() -> {
        });
        businessRuleEngine.addAction(() -> {
        });

        assertEquals(2, businessRuleEngine.count());
    }

    @Test
    public void shouldPerformAnActionWithFacts() {
        final Action mockAction = mock(Action.class);
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockedFacts);

        businessRuleEngine.addAction(mockAction);
        businessRuleEngine.run();

        verify(mockAction).perform(mockFacts);
    }


}
