package com.xidian.iot.database.pojo;

import java.util.ArrayList;
import java.util.List;

public class NodeCmdProtExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NodeCmdProtExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andNcpIdIsNull() {
            addCriterion("ncp_id is null");
            return (Criteria) this;
        }

        public Criteria andNcpIdIsNotNull() {
            addCriterion("ncp_id is not null");
            return (Criteria) this;
        }

        public Criteria andNcpIdEqualTo(Integer value) {
            addCriterion("ncp_id =", value, "ncpId");
            return (Criteria) this;
        }

        public Criteria andNcpIdNotEqualTo(Integer value) {
            addCriterion("ncp_id <>", value, "ncpId");
            return (Criteria) this;
        }

        public Criteria andNcpIdGreaterThan(Integer value) {
            addCriterion("ncp_id >", value, "ncpId");
            return (Criteria) this;
        }

        public Criteria andNcpIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ncp_id >=", value, "ncpId");
            return (Criteria) this;
        }

        public Criteria andNcpIdLessThan(Integer value) {
            addCriterion("ncp_id <", value, "ncpId");
            return (Criteria) this;
        }

        public Criteria andNcpIdLessThanOrEqualTo(Integer value) {
            addCriterion("ncp_id <=", value, "ncpId");
            return (Criteria) this;
        }

        public Criteria andNcpIdIn(List<Integer> values) {
            addCriterion("ncp_id in", values, "ncpId");
            return (Criteria) this;
        }

        public Criteria andNcpIdNotIn(List<Integer> values) {
            addCriterion("ncp_id not in", values, "ncpId");
            return (Criteria) this;
        }

        public Criteria andNcpIdBetween(Integer value1, Integer value2) {
            addCriterion("ncp_id between", value1, value2, "ncpId");
            return (Criteria) this;
        }

        public Criteria andNcpIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ncp_id not between", value1, value2, "ncpId");
            return (Criteria) this;
        }

        public Criteria andNodeIdIsNull() {
            addCriterion("node_id is null");
            return (Criteria) this;
        }

        public Criteria andNodeIdIsNotNull() {
            addCriterion("node_id is not null");
            return (Criteria) this;
        }

        public Criteria andNodeIdEqualTo(Integer value) {
            addCriterion("node_id =", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdNotEqualTo(Integer value) {
            addCriterion("node_id <>", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdGreaterThan(Integer value) {
            addCriterion("node_id >", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("node_id >=", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdLessThan(Integer value) {
            addCriterion("node_id <", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdLessThanOrEqualTo(Integer value) {
            addCriterion("node_id <=", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdIn(List<Integer> values) {
            addCriterion("node_id in", values, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdNotIn(List<Integer> values) {
            addCriterion("node_id not in", values, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdBetween(Integer value1, Integer value2) {
            addCriterion("node_id between", value1, value2, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("node_id not between", value1, value2, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNcpTypeIsNull() {
            addCriterion("ncp_type is null");
            return (Criteria) this;
        }

        public Criteria andNcpTypeIsNotNull() {
            addCriterion("ncp_type is not null");
            return (Criteria) this;
        }

        public Criteria andNcpTypeEqualTo(String value) {
            addCriterion("ncp_type =", value, "ncpType");
            return (Criteria) this;
        }

        public Criteria andNcpTypeNotEqualTo(String value) {
            addCriterion("ncp_type <>", value, "ncpType");
            return (Criteria) this;
        }

        public Criteria andNcpTypeGreaterThan(String value) {
            addCriterion("ncp_type >", value, "ncpType");
            return (Criteria) this;
        }

        public Criteria andNcpTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ncp_type >=", value, "ncpType");
            return (Criteria) this;
        }

        public Criteria andNcpTypeLessThan(String value) {
            addCriterion("ncp_type <", value, "ncpType");
            return (Criteria) this;
        }

        public Criteria andNcpTypeLessThanOrEqualTo(String value) {
            addCriterion("ncp_type <=", value, "ncpType");
            return (Criteria) this;
        }

        public Criteria andNcpTypeLike(String value) {
            addCriterion("ncp_type like", value, "ncpType");
            return (Criteria) this;
        }

        public Criteria andNcpTypeNotLike(String value) {
            addCriterion("ncp_type not like", value, "ncpType");
            return (Criteria) this;
        }

        public Criteria andNcpTypeIn(List<String> values) {
            addCriterion("ncp_type in", values, "ncpType");
            return (Criteria) this;
        }

        public Criteria andNcpTypeNotIn(List<String> values) {
            addCriterion("ncp_type not in", values, "ncpType");
            return (Criteria) this;
        }

        public Criteria andNcpTypeBetween(String value1, String value2) {
            addCriterion("ncp_type between", value1, value2, "ncpType");
            return (Criteria) this;
        }

        public Criteria andNcpTypeNotBetween(String value1, String value2) {
            addCriterion("ncp_type not between", value1, value2, "ncpType");
            return (Criteria) this;
        }

        public Criteria andNcpDownIsNull() {
            addCriterion("ncp_down is null");
            return (Criteria) this;
        }

        public Criteria andNcpDownIsNotNull() {
            addCriterion("ncp_down is not null");
            return (Criteria) this;
        }

        public Criteria andNcpDownEqualTo(String value) {
            addCriterion("ncp_down =", value, "ncpDown");
            return (Criteria) this;
        }

        public Criteria andNcpDownNotEqualTo(String value) {
            addCriterion("ncp_down <>", value, "ncpDown");
            return (Criteria) this;
        }

        public Criteria andNcpDownGreaterThan(String value) {
            addCriterion("ncp_down >", value, "ncpDown");
            return (Criteria) this;
        }

        public Criteria andNcpDownGreaterThanOrEqualTo(String value) {
            addCriterion("ncp_down >=", value, "ncpDown");
            return (Criteria) this;
        }

        public Criteria andNcpDownLessThan(String value) {
            addCriterion("ncp_down <", value, "ncpDown");
            return (Criteria) this;
        }

        public Criteria andNcpDownLessThanOrEqualTo(String value) {
            addCriterion("ncp_down <=", value, "ncpDown");
            return (Criteria) this;
        }

        public Criteria andNcpDownLike(String value) {
            addCriterion("ncp_down like", value, "ncpDown");
            return (Criteria) this;
        }

        public Criteria andNcpDownNotLike(String value) {
            addCriterion("ncp_down not like", value, "ncpDown");
            return (Criteria) this;
        }

        public Criteria andNcpDownIn(List<String> values) {
            addCriterion("ncp_down in", values, "ncpDown");
            return (Criteria) this;
        }

        public Criteria andNcpDownNotIn(List<String> values) {
            addCriterion("ncp_down not in", values, "ncpDown");
            return (Criteria) this;
        }

        public Criteria andNcpDownBetween(String value1, String value2) {
            addCriterion("ncp_down between", value1, value2, "ncpDown");
            return (Criteria) this;
        }

        public Criteria andNcpDownNotBetween(String value1, String value2) {
            addCriterion("ncp_down not between", value1, value2, "ncpDown");
            return (Criteria) this;
        }

        public Criteria andNcpUpIsNull() {
            addCriterion("ncp_up is null");
            return (Criteria) this;
        }

        public Criteria andNcpUpIsNotNull() {
            addCriterion("ncp_up is not null");
            return (Criteria) this;
        }

        public Criteria andNcpUpEqualTo(String value) {
            addCriterion("ncp_up =", value, "ncpUp");
            return (Criteria) this;
        }

        public Criteria andNcpUpNotEqualTo(String value) {
            addCriterion("ncp_up <>", value, "ncpUp");
            return (Criteria) this;
        }

        public Criteria andNcpUpGreaterThan(String value) {
            addCriterion("ncp_up >", value, "ncpUp");
            return (Criteria) this;
        }

        public Criteria andNcpUpGreaterThanOrEqualTo(String value) {
            addCriterion("ncp_up >=", value, "ncpUp");
            return (Criteria) this;
        }

        public Criteria andNcpUpLessThan(String value) {
            addCriterion("ncp_up <", value, "ncpUp");
            return (Criteria) this;
        }

        public Criteria andNcpUpLessThanOrEqualTo(String value) {
            addCriterion("ncp_up <=", value, "ncpUp");
            return (Criteria) this;
        }

        public Criteria andNcpUpLike(String value) {
            addCriterion("ncp_up like", value, "ncpUp");
            return (Criteria) this;
        }

        public Criteria andNcpUpNotLike(String value) {
            addCriterion("ncp_up not like", value, "ncpUp");
            return (Criteria) this;
        }

        public Criteria andNcpUpIn(List<String> values) {
            addCriterion("ncp_up in", values, "ncpUp");
            return (Criteria) this;
        }

        public Criteria andNcpUpNotIn(List<String> values) {
            addCriterion("ncp_up not in", values, "ncpUp");
            return (Criteria) this;
        }

        public Criteria andNcpUpBetween(String value1, String value2) {
            addCriterion("ncp_up between", value1, value2, "ncpUp");
            return (Criteria) this;
        }

        public Criteria andNcpUpNotBetween(String value1, String value2) {
            addCriterion("ncp_up not between", value1, value2, "ncpUp");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}