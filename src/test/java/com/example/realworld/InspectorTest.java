package com.example.realworld;

import com.example.realworld.chapter5.ConditionalAction;
import com.example.realworld.chapter5.Diagnosis;
import com.example.realworld.chapter5.Facts;
import com.example.realworld.chapter5.Inspector;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class InspectorTest {

    @Test
    public void inspectOneConditionEvaluatesTrue() {
        final Facts facts = new Facts();
        facts.setFacts("jobTitle", "CEO");

        final ConditionalAction conditionalAction = new jobTitleCondition();
        final Inspector inspector = new Inspector(conditionalAction);

        final List<Diagnosis> diagnosisList = inspector.inspect(facts);

        assertEquals(1, diagnosisList.size());
        assertEquals(true, diagnosisList.get(0).isPositive());

    }

    private static class jobTitleCondition implements ConditionalAction {

        @Override
        public void perform(Facts facts) {

        }

        @Override
        public boolean evaluate(Facts facts) {
            return "CEO".equals(facts.getFacts("jobTitle"));
        }
    }

}
