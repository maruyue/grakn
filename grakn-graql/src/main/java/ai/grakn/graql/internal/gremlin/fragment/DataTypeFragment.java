/*
 * Grakn - A Distributed Semantic Database
 * Copyright (C) 2016  Grakn Labs Limited
 *
 * Grakn is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Grakn is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Grakn. If not, see <http://www.gnu.org/licenses/gpl.txt>.
 */

package ai.grakn.graql.internal.gremlin.fragment;

import ai.grakn.GraknGraph;
import ai.grakn.concept.ResourceType;
import ai.grakn.graql.Var;
import ai.grakn.graql.admin.VarProperty;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.structure.Element;

import static ai.grakn.util.Schema.VertexProperty.DATA_TYPE;

class DataTypeFragment extends AbstractFragment {

    private final ResourceType.DataType dataType;

    DataTypeFragment(VarProperty varProperty, Var start, ResourceType.DataType dataType) {
        super(varProperty, start);
        this.dataType = dataType;
    }

    @Override
    public GraphTraversal<Element, ? extends Element> applyTraversal(
            GraphTraversal<Element, ? extends Element> traversal, GraknGraph graph) {
        return traversal.has(DATA_TYPE.name(), dataType.getName());
    }

    @Override
    public String getName() {
        return "[datatype:" + dataType.getName() + "]";
    }

    @Override
    public double fragmentCost() {
        return COST_DATA_TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DataTypeFragment that = (DataTypeFragment) o;

        return dataType != null ? dataType.equals(that.dataType) : that.dataType == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        return result;
    }
}
