/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright 2013-2013 the original author or authors.
 */
package org.assertj.neo4j.api;

import static org.assertj.neo4j.error.ShouldHaveEndNode.shouldHaveEndNode;
import static org.assertj.neo4j.error.ShouldHaveRelationshipType.shouldHaveRelationshipType;
import static org.assertj.neo4j.error.ShouldHaveStartNode.shouldHaveStartNode;
import static org.assertj.neo4j.error.ShouldHaveStartOrEndNode.shouldHaveStartOrEndNode;
import static org.assertj.neo4j.error.ShouldNotHaveRelationshipType.shouldNotHaveRelationshipType;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Failures;
import org.assertj.core.internal.Objects;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;

/**
 * Assertions for Neo4J {@link org.neo4j.graphdb.Relationship}
 * 
 * @author Florent Biville
 */
public class RelationshipAssert extends AbstractAssert<RelationshipAssert, Relationship> {

  protected RelationshipAssert(Relationship actual) {
    super(actual, RelationshipAssert.class);
  }

  public Relationship getActual() {
    return actual;
  }

  /**
   * Verifies that the actual {@link org.neo4j.graphdb.Relationship} starts with the given node<br/>
   * <p>
   * Example:
   * 
   * <pre>
   * GraphDatabaseService graph = new TestGraphDatabaseFactory().newImpermanentDatabase();
   * // [...] creation of homerNode, doughnutNode
   * Relationship love = homerNode.createRelationshipTo(doughnutNode, DynamicRelationshipType.withName(&quot;LOVES&quot;));
   * 
   * assertThat(love).hasStart(homerNode);
   * </pre>
   * 
   * If the <code>node</code> is {@code null}, an {@link IllegalArgumentException} is thrown.
   * <p>
   * 
   * @param node the node from which the actual {@link org.neo4j.graphdb.Relationship} may start from
   * @return this {@link RelationshipAssert} for assertions chaining
   * 
   * @throws IllegalArgumentException if <code>node</code> is {@code null}.
   * @throws AssertionError if the actual {@link org.neo4j.graphdb.Relationship} does not start with the given node
   */
  public RelationshipAssert hasStart(Node node) {
    Objects.instance().assertNotNull(info, actual);

    Node actualStartNode = actual.getStartNode();

    checkActualRelationshipNode(actualStartNode, "The actual start node should not be null");
    checkArgumentNode(node, "The start node to look for should not be null");

    if (!actualStartNode.equals(node)) {
      throw Failures.instance().failure(info, shouldHaveStartNode(actual, node));
    }
    return this;
  }

  /**
   * Verifies that the actual {@link org.neo4j.graphdb.Relationship} ends with the given node<br/>
   * <p>
   * Example:
   * 
   * <pre>
   * GraphDatabaseService graph = new TestGraphDatabaseFactory().newImpermanentDatabase();
   * // [...] creation of homerNode, doughnutNode
   * Relationship love = homerNode.createRelationshipTo(doughnutNode, DynamicRelationshipType.withName(&quot;LOVES&quot;));
   * 
   * assertThat(love).hasEnd(doughnutNode);
   * </pre>
   * 
   * If the <code>node</code> is {@code null}, an {@link IllegalArgumentException} is thrown.
   * <p>
   * 
   * @param node the node from which the actual {@link org.neo4j.graphdb.Relationship} may end to
   * @return this {@link RelationshipAssert} for assertions chaining
   * 
   * @throws IllegalArgumentException if <code>node</code> is {@code null}.
   * @throws AssertionError if the actual {@link org.neo4j.graphdb.Relationship} does not end with the given node
   */
  public RelationshipAssert hasEnd(Node node) {
    Objects.instance().assertNotNull(info, actual);

    Node actualEndNode = actual.getEndNode();

    checkActualRelationshipNode(actualEndNode, "The actual end node should not be null");
    checkArgumentNode(node, "The end node to look for should not be null");

    if (!actualEndNode.equals(node)) {
      throw Failures.instance().failure(info, shouldHaveEndNode(actual, node));
    }
    return this;
  }

  /**
   * Verifies that the actual {@link org.neo4j.graphdb.Relationship} either starts or ends with the given node<br/>
   * <p>
   * Example:
   * 
   * <pre>
   * GraphDatabaseService graph = new TestGraphDatabaseFactory().newImpermanentDatabase();
   * // [...] creation of homerNode, doughnutNode
   * Relationship love = homerNode.createRelationshipTo(doughnutNode, DynamicRelationshipType.withName(&quot;LOVES&quot;));
   * 
   * assertThat(love).hasStartOrEnd(homerNode);
   * assertThat(love).hasStartOrEnd(doughnutNode);
   * </pre>
   * 
   * If the <code>node</code> is {@code null}, an {@link IllegalArgumentException} is thrown.
   * <p>
   * 
   * @param node the node from which the actual {@link org.neo4j.graphdb.Relationship} may either start from or end to
   * @return this {@link RelationshipAssert} for assertions chaining
   * 
   * @throws IllegalArgumentException if <code>node</code> is {@code null}.
   * @throws AssertionError if the actual {@link org.neo4j.graphdb.Relationship} does not start neither end with the
   *           given node
   */
  public RelationshipAssert hasStartOrEnd(Node node) {
    Objects.instance().assertNotNull(info, actual);

    Node actualStartNode = actual.getStartNode();
    Node actualEndNode = actual.getEndNode();

    checkActualRelationshipNode(actualStartNode, "The actual start node should not be null");
    checkActualRelationshipNode(actualEndNode, "The actual end node should not be null");
    checkArgumentNode(node, "The node to look for should not be null");

    if (!(actualStartNode.equals(node) || actualEndNode.equals(node))) {
      throw Failures.instance().failure(info, shouldHaveStartOrEndNode(actual, node));
    }
    return this;
  }

  /**
   * Verifies that the actual {@link org.neo4j.graphdb.Relationship} has the given type<br/>
   * <p>
   * Example:
   * 
   * <pre>
   * GraphDatabaseService graph = new TestGraphDatabaseFactory().newImpermanentDatabase();
   * // [...] creation of homerNode, doughnutNode
   * RelationshipType loveType = DynamicRelationshipType.withName(&quot;LOVES&quot;);
   * Relationship love = homerNode.createRelationshipTo(doughnutNode, loveType);
   * 
   * assertThat(love).hasType(loveType);
   * </pre>
   * 
   * If the <code>node</code> is {@code null}, an {@link IllegalArgumentException} is thrown.
   * <p>
   * 
   * @param relationshipType a {@link org.neo4j.graphdb.Relationship} type
   * @return this {@link RelationshipAssert} for assertions chaining
   * 
   * @throws IllegalArgumentException if <code>relationshipType</code> is {@code null}.
   * @throws AssertionError if the actual {@link org.neo4j.graphdb.Relationship} does not have the given type
   */
  public RelationshipAssert hasType(RelationshipType relationshipType) {
    Objects.instance().assertNotNull(info, actual);

    RelationshipType actualType = actual.getType();
    if (actualType == null) {
      throw new IllegalStateException("The actual relationship type should not be null");
    }

    if (relationshipType == null) {
      throw new IllegalArgumentException("The relationship type to look for should not be null");
    }

    if (!actualType.equals(relationshipType)) {
      throw Failures.instance().failure(info, shouldHaveRelationshipType(actual, relationshipType));
    }
    return this;
  }

  /**
   * Verifies that the actual {@link org.neo4j.graphdb.Relationship} does NOT have the given type<br/>
   * <p>
   * Example:
   * 
   * <pre>
   * GraphDatabaseService graph = new TestGraphDatabaseFactory().newImpermanentDatabase();
   * // [...] creation of homerNode, doughnutNode
   * Relationship love = homerNode.createRelationshipTo(doughnutNode, DynamicRelationshipType.withName(&quot;LOVES&quot;));
   * 
   * assertThat(love).hasNotType(DynamicRelationshipType.withName(&quot;HATES&quot;));
   * </pre>
   * 
   * If the <code>node</code> is {@code null}, an {@link IllegalArgumentException} is thrown.
   * <p>
   * 
   * @param relationshipType a {@link org.neo4j.graphdb.Relationship} type
   * @return this {@link RelationshipAssert} for assertions chaining
   * 
   * @throws IllegalArgumentException if <code>relationshipType</code> is {@code null}.
   * @throws AssertionError if the actual {@link org.neo4j.graphdb.Relationship} does not have the given type
   */
  public RelationshipAssert hasNotType(RelationshipType relationshipType) {
    Objects.instance().assertNotNull(info, actual);

    RelationshipType actualType = actual.getType();
    if (actualType == null) {
      throw new IllegalStateException("The actual relationship type should not be null");
    }

    if (relationshipType == null) {
      throw new IllegalArgumentException("The relationship type to look for should not be null");
    }

    if (actualType.equals(relationshipType)) {
      throw Failures.instance().failure(info, shouldNotHaveRelationshipType(actual, relationshipType));
    }
    return this;
  }

  private static void checkActualRelationshipNode(Node node, String errorMessage) {
    if (node == null) {
      throw new IllegalStateException(errorMessage);
    }
  }

  private static void checkArgumentNode(Node node, String errorMessage) {
    if (node == null) {
      throw new IllegalArgumentException(errorMessage);
    }
  }
}