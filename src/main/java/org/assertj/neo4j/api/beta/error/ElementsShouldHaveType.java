package org.assertj.neo4j.api.beta.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.util.Strings;
import org.assertj.neo4j.api.beta.type.Nodes;
import org.assertj.neo4j.api.beta.type.Relationships;
import org.assertj.neo4j.api.beta.util.Entities;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author patouche - 25/11/2020
 */
public class ElementsShouldHaveType extends BasicErrorMessageFactory {

    /**
     * Creates a new <code>{@link BasicErrorMessageFactory}</code>.
     *
     * @param actual        the relationships
     * @param expectedType  the expected type.
     * @param missingActual the relationships that doesn't have the expected type
     */
    public ElementsShouldHaveType(final List<Relationships.DbRelationship> actual, final String expectedType,
            final List<Relationships.DbRelationship> missingActual) {
        super("%nExpecting relationships:%n"
              + "  <%s> to be of type:%n"
              + "  <%s>%n"
              + "but some relationships have an other type:%n%n"
              + Strings.escapePercent(describeItems(expectedType, missingActual)),
              Entities.outputIds(actual), expectedType

        );
    }

    private static String describeItems(
            final String expectedType, final List<Relationships.DbRelationship> relationships) {
        return relationships.stream()
                .map(item -> describeItem(expectedType, item))
                .collect(Collectors.joining(String.format("%n%n")));
    }

    private static String describeItem(final String expectedType, final Relationships.DbRelationship relationship) {

        return String.format(
                "  - %s doesn't have the expected type:%n"
                + "    Actual: <%s>%n"
                + "    Expected: <%s>",
                Entities.outputId(relationship), relationship.getType(), expectedType
        );
    }

    public static ElementsShouldHaveType create(
            final List<Relationships.DbRelationship> actual, final String expectedType,
            final List<Relationships.DbRelationship> missingActual) {
        return new ElementsShouldHaveType(actual, expectedType, missingActual);
    }
}