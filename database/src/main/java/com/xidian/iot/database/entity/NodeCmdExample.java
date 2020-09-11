package com.xidian.iot.database.entity;

import java.util.ArrayList;
import java.util.List;

public class NodeCmdExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NodeCmdExample() {
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

        public Criteria andNcIdIsNull() {
            addCriterion("nc_id is null");
            return (Criteria) this;
        }

        public Criteria andNcIdIsNotNull() {
            addCriterion("nc_id is not null");
            return (Criteria) this;
        }

        public Criteria andNcIdEqualTo(Long value) {
            addCriterion("nc_id =", value, "ncId");
            return (Criteria) this;
        }

        public Criteria andNcIdNotEqualTo(Long value) {
            addCriterion("nc_id <>", value, "ncId");
            return (Criteria) this;
        }

        public Criteria andNcIdGreaterThan(Long value) {
            addCriterion("nc_id >", value, "ncId");
            return (Criteria) this;
        }

        public Criteria andNcIdGreaterThanOrEqualTo(Long value) {
            addCriterion("nc_id >=", value, "ncId");
            return (Criteria) this;
        }

        public Criteria andNcIdLessThan(Long value) {
            addCriterion("nc_id <", value, "ncId");
            return (Criteria) this;
        }

        public Criteria andNcIdLessThanOrEqualTo(Long value) {
            addCriterion("nc_id <=", value, "ncId");
            return (Criteria) this;
        }

        public Criteria andNcIdIn(List<Long> values) {
            addCriterion("nc_id in", values, "ncId");
            return (Criteria) this;
        }

        public Criteria andNcIdNotIn(List<Long> values) {
            addCriterion("nc_id not in", values, "ncId");
            return (Criteria) this;
        }

        public Criteria andNcIdBetween(Long value1, Long value2) {
            addCriterion("nc_id between", value1, value2, "ncId");
            return (Criteria) this;
        }

        public Criteria andNcIdNotBetween(Long value1, Long value2) {
            addCriterion("nc_id not between", value1, value2, "ncId");
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

        public Criteria andNodeIdEqualTo(Long value) {
            addCriterion("node_id =", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdNotEqualTo(Long value) {
            addCriterion("node_id <>", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdGreaterThan(Long value) {
            addCriterion("node_id >", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("node_id >=", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdLessThan(Long value) {
            addCriterion("node_id <", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdLessThanOrEqualTo(Long value) {
            addCriterion("node_id <=", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdIn(List<Long> values) {
            addCriterion("node_id in", values, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdNotIn(List<Long> values) {
            addCriterion("node_id not in", values, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdBetween(Long value1, Long value2) {
            addCriterion("node_id between", value1, value2, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNodeIdNotBetween(Long value1, Long value2) {
            addCriterion("node_id not between", value1, value2, "nodeId");
            return (Criteria) this;
        }

        public Criteria andNcgIdIsNull() {
            addCriterion("ncg_id is null");
            return (Criteria) this;
        }

        public Criteria andNcgIdIsNotNull() {
            addCriterion("ncg_id is not null");
            return (Criteria) this;
        }

        public Criteria andNcgIdEqualTo(Long value) {
            addCriterion("ncg_id =", value, "ncgId");
            return (Criteria) this;
        }

        public Criteria andNcgIdNotEqualTo(Long value) {
            addCriterion("ncg_id <>", value, "ncgId");
            return (Criteria) this;
        }

        public Criteria andNcgIdGreaterThan(Long value) {
            addCriterion("ncg_id >", value, "ncgId");
            return (Criteria) this;
        }

        public Criteria andNcgIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ncg_id >=", value, "ncgId");
            return (Criteria) this;
        }

        public Criteria andNcgIdLessThan(Long value) {
            addCriterion("ncg_id <", value, "ncgId");
            return (Criteria) this;
        }

        public Criteria andNcgIdLessThanOrEqualTo(Long value) {
            addCriterion("ncg_id <=", value, "ncgId");
            return (Criteria) this;
        }

        public Criteria andNcgIdIn(List<Long> values) {
            addCriterion("ncg_id in", values, "ncgId");
            return (Criteria) this;
        }

        public Criteria andNcgIdNotIn(List<Long> values) {
            addCriterion("ncg_id not in", values, "ncgId");
            return (Criteria) this;
        }

        public Criteria andNcgIdBetween(Long value1, Long value2) {
            addCriterion("ncg_id between", value1, value2, "ncgId");
            return (Criteria) this;
        }

        public Criteria andNcgIdNotBetween(Long value1, Long value2) {
            addCriterion("ncg_id not between", value1, value2, "ncgId");
            return (Criteria) this;
        }

        public Criteria andNcContentIsNull() {
            addCriterion("nc_content is null");
            return (Criteria) this;
        }

        public Criteria andNcContentIsNotNull() {
            addCriterion("nc_content is not null");
            return (Criteria) this;
        }

        public Criteria andNcContentEqualTo(String value) {
            addCriterion("nc_content =", value, "ncContent");
            return (Criteria) this;
        }

        public Criteria andNcContentNotEqualTo(String value) {
            addCriterion("nc_content <>", value, "ncContent");
            return (Criteria) this;
        }

        public Criteria andNcContentGreaterThan(String value) {
            addCriterion("nc_content >", value, "ncContent");
            return (Criteria) this;
        }

        public Criteria andNcContentGreaterThanOrEqualTo(String value) {
            addCriterion("nc_content >=", value, "ncContent");
            return (Criteria) this;
        }

        public Criteria andNcContentLessThan(String value) {
            addCriterion("nc_content <", value, "ncContent");
            return (Criteria) this;
        }

        public Criteria andNcContentLessThanOrEqualTo(String value) {
            addCriterion("nc_content <=", value, "ncContent");
            return (Criteria) this;
        }

        public Criteria andNcContentLike(String value) {
            addCriterion("nc_content like", value, "ncContent");
            return (Criteria) this;
        }

        public Criteria andNcContentNotLike(String value) {
            addCriterion("nc_content not like", value, "ncContent");
            return (Criteria) this;
        }

        public Criteria andNcContentIn(List<String> values) {
            addCriterion("nc_content in", values, "ncContent");
            return (Criteria) this;
        }

        public Criteria andNcContentNotIn(List<String> values) {
            addCriterion("nc_content not in", values, "ncContent");
            return (Criteria) this;
        }

        public Criteria andNcContentBetween(String value1, String value2) {
            addCriterion("nc_content between", value1, value2, "ncContent");
            return (Criteria) this;
        }

        public Criteria andNcContentNotBetween(String value1, String value2) {
            addCriterion("nc_content not between", value1, value2, "ncContent");
            return (Criteria) this;
        }

        public Criteria andNcNameIsNull() {
            addCriterion("nc_name is null");
            return (Criteria) this;
        }

        public Criteria andNcNameIsNotNull() {
            addCriterion("nc_name is not null");
            return (Criteria) this;
        }

        public Criteria andNcNameEqualTo(String value) {
            addCriterion("nc_name =", value, "ncName");
            return (Criteria) this;
        }

        public Criteria andNcNameNotEqualTo(String value) {
            addCriterion("nc_name <>", value, "ncName");
            return (Criteria) this;
        }

        public Criteria andNcNameGreaterThan(String value) {
            addCriterion("nc_name >", value, "ncName");
            return (Criteria) this;
        }

        public Criteria andNcNameGreaterThanOrEqualTo(String value) {
            addCriterion("nc_name >=", value, "ncName");
            return (Criteria) this;
        }

        public Criteria andNcNameLessThan(String value) {
            addCriterion("nc_name <", value, "ncName");
            return (Criteria) this;
        }

        public Criteria andNcNameLessThanOrEqualTo(String value) {
            addCriterion("nc_name <=", value, "ncName");
            return (Criteria) this;
        }

        public Criteria andNcNameLike(String value) {
            addCriterion("nc_name like", value, "ncName");
            return (Criteria) this;
        }

        public Criteria andNcNameNotLike(String value) {
            addCriterion("nc_name not like", value, "ncName");
            return (Criteria) this;
        }

        public Criteria andNcNameIn(List<String> values) {
            addCriterion("nc_name in", values, "ncName");
            return (Criteria) this;
        }

        public Criteria andNcNameNotIn(List<String> values) {
            addCriterion("nc_name not in", values, "ncName");
            return (Criteria) this;
        }

        public Criteria andNcNameBetween(String value1, String value2) {
            addCriterion("nc_name between", value1, value2, "ncName");
            return (Criteria) this;
        }

        public Criteria andNcNameNotBetween(String value1, String value2) {
            addCriterion("nc_name not between", value1, value2, "ncName");
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