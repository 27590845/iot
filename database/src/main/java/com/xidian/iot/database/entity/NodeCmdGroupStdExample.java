package com.xidian.iot.database.entity;

import java.util.ArrayList;
import java.util.List;

public class NodeCmdGroupStdExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NodeCmdGroupStdExample() {
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

        public Criteria andNcgsIdIsNull() {
            addCriterion("ncgs_id is null");
            return (Criteria) this;
        }

        public Criteria andNcgsIdIsNotNull() {
            addCriterion("ncgs_id is not null");
            return (Criteria) this;
        }

        public Criteria andNcgsIdEqualTo(Long value) {
            addCriterion("ncgs_id =", value, "ncgsId");
            return (Criteria) this;
        }

        public Criteria andNcgsIdNotEqualTo(Long value) {
            addCriterion("ncgs_id <>", value, "ncgsId");
            return (Criteria) this;
        }

        public Criteria andNcgsIdGreaterThan(Long value) {
            addCriterion("ncgs_id >", value, "ncgsId");
            return (Criteria) this;
        }

        public Criteria andNcgsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ncgs_id >=", value, "ncgsId");
            return (Criteria) this;
        }

        public Criteria andNcgsIdLessThan(Long value) {
            addCriterion("ncgs_id <", value, "ncgsId");
            return (Criteria) this;
        }

        public Criteria andNcgsIdLessThanOrEqualTo(Long value) {
            addCriterion("ncgs_id <=", value, "ncgsId");
            return (Criteria) this;
        }

        public Criteria andNcgsIdIn(List<Long> values) {
            addCriterion("ncgs_id in", values, "ncgsId");
            return (Criteria) this;
        }

        public Criteria andNcgsIdNotIn(List<Long> values) {
            addCriterion("ncgs_id not in", values, "ncgsId");
            return (Criteria) this;
        }

        public Criteria andNcgsIdBetween(Long value1, Long value2) {
            addCriterion("ncgs_id between", value1, value2, "ncgsId");
            return (Criteria) this;
        }

        public Criteria andNcgsIdNotBetween(Long value1, Long value2) {
            addCriterion("ncgs_id not between", value1, value2, "ncgsId");
            return (Criteria) this;
        }

        public Criteria andNcgsNameIsNull() {
            addCriterion("ncgs_name is null");
            return (Criteria) this;
        }

        public Criteria andNcgsNameIsNotNull() {
            addCriterion("ncgs_name is not null");
            return (Criteria) this;
        }

        public Criteria andNcgsNameEqualTo(String value) {
            addCriterion("ncgs_name =", value, "ncgsName");
            return (Criteria) this;
        }

        public Criteria andNcgsNameNotEqualTo(String value) {
            addCriterion("ncgs_name <>", value, "ncgsName");
            return (Criteria) this;
        }

        public Criteria andNcgsNameGreaterThan(String value) {
            addCriterion("ncgs_name >", value, "ncgsName");
            return (Criteria) this;
        }

        public Criteria andNcgsNameGreaterThanOrEqualTo(String value) {
            addCriterion("ncgs_name >=", value, "ncgsName");
            return (Criteria) this;
        }

        public Criteria andNcgsNameLessThan(String value) {
            addCriterion("ncgs_name <", value, "ncgsName");
            return (Criteria) this;
        }

        public Criteria andNcgsNameLessThanOrEqualTo(String value) {
            addCriterion("ncgs_name <=", value, "ncgsName");
            return (Criteria) this;
        }

        public Criteria andNcgsNameLike(String value) {
            addCriterion("ncgs_name like", value, "ncgsName");
            return (Criteria) this;
        }

        public Criteria andNcgsNameNotLike(String value) {
            addCriterion("ncgs_name not like", value, "ncgsName");
            return (Criteria) this;
        }

        public Criteria andNcgsNameIn(List<String> values) {
            addCriterion("ncgs_name in", values, "ncgsName");
            return (Criteria) this;
        }

        public Criteria andNcgsNameNotIn(List<String> values) {
            addCriterion("ncgs_name not in", values, "ncgsName");
            return (Criteria) this;
        }

        public Criteria andNcgsNameBetween(String value1, String value2) {
            addCriterion("ncgs_name between", value1, value2, "ncgsName");
            return (Criteria) this;
        }

        public Criteria andNcgsNameNotBetween(String value1, String value2) {
            addCriterion("ncgs_name not between", value1, value2, "ncgsName");
            return (Criteria) this;
        }

        public Criteria andNcgsKeyIsNull() {
            addCriterion("ncgs_key is null");
            return (Criteria) this;
        }

        public Criteria andNcgsKeyIsNotNull() {
            addCriterion("ncgs_key is not null");
            return (Criteria) this;
        }

        public Criteria andNcgsKeyEqualTo(String value) {
            addCriterion("ncgs_key =", value, "ncgsKey");
            return (Criteria) this;
        }

        public Criteria andNcgsKeyNotEqualTo(String value) {
            addCriterion("ncgs_key <>", value, "ncgsKey");
            return (Criteria) this;
        }

        public Criteria andNcgsKeyGreaterThan(String value) {
            addCriterion("ncgs_key >", value, "ncgsKey");
            return (Criteria) this;
        }

        public Criteria andNcgsKeyGreaterThanOrEqualTo(String value) {
            addCriterion("ncgs_key >=", value, "ncgsKey");
            return (Criteria) this;
        }

        public Criteria andNcgsKeyLessThan(String value) {
            addCriterion("ncgs_key <", value, "ncgsKey");
            return (Criteria) this;
        }

        public Criteria andNcgsKeyLessThanOrEqualTo(String value) {
            addCriterion("ncgs_key <=", value, "ncgsKey");
            return (Criteria) this;
        }

        public Criteria andNcgsKeyLike(String value) {
            addCriterion("ncgs_key like", value, "ncgsKey");
            return (Criteria) this;
        }

        public Criteria andNcgsKeyNotLike(String value) {
            addCriterion("ncgs_key not like", value, "ncgsKey");
            return (Criteria) this;
        }

        public Criteria andNcgsKeyIn(List<String> values) {
            addCriterion("ncgs_key in", values, "ncgsKey");
            return (Criteria) this;
        }

        public Criteria andNcgsKeyNotIn(List<String> values) {
            addCriterion("ncgs_key not in", values, "ncgsKey");
            return (Criteria) this;
        }

        public Criteria andNcgsKeyBetween(String value1, String value2) {
            addCriterion("ncgs_key between", value1, value2, "ncgsKey");
            return (Criteria) this;
        }

        public Criteria andNcgsKeyNotBetween(String value1, String value2) {
            addCriterion("ncgs_key not between", value1, value2, "ncgsKey");
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