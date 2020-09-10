package com.xidian.iot.database.entity;

import java.util.ArrayList;
import java.util.List;

public class NodeCmdStdExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NodeCmdStdExample() {
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

        public Criteria andNcsIdIsNull() {
            addCriterion("ncs_id is null");
            return (Criteria) this;
        }

        public Criteria andNcsIdIsNotNull() {
            addCriterion("ncs_id is not null");
            return (Criteria) this;
        }

        public Criteria andNcsIdEqualTo(Long value) {
            addCriterion("ncs_id =", value, "ncsId");
            return (Criteria) this;
        }

        public Criteria andNcsIdNotEqualTo(Long value) {
            addCriterion("ncs_id <>", value, "ncsId");
            return (Criteria) this;
        }

        public Criteria andNcsIdGreaterThan(Long value) {
            addCriterion("ncs_id >", value, "ncsId");
            return (Criteria) this;
        }

        public Criteria andNcsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ncs_id >=", value, "ncsId");
            return (Criteria) this;
        }

        public Criteria andNcsIdLessThan(Long value) {
            addCriterion("ncs_id <", value, "ncsId");
            return (Criteria) this;
        }

        public Criteria andNcsIdLessThanOrEqualTo(Long value) {
            addCriterion("ncs_id <=", value, "ncsId");
            return (Criteria) this;
        }

        public Criteria andNcsIdIn(List<Long> values) {
            addCriterion("ncs_id in", values, "ncsId");
            return (Criteria) this;
        }

        public Criteria andNcsIdNotIn(List<Long> values) {
            addCriterion("ncs_id not in", values, "ncsId");
            return (Criteria) this;
        }

        public Criteria andNcsIdBetween(Long value1, Long value2) {
            addCriterion("ncs_id between", value1, value2, "ncsId");
            return (Criteria) this;
        }

        public Criteria andNcsIdNotBetween(Long value1, Long value2) {
            addCriterion("ncs_id not between", value1, value2, "ncsId");
            return (Criteria) this;
        }

        public Criteria andNcsKeyIsNull() {
            addCriterion("ncs_key is null");
            return (Criteria) this;
        }

        public Criteria andNcsKeyIsNotNull() {
            addCriterion("ncs_key is not null");
            return (Criteria) this;
        }

        public Criteria andNcsKeyEqualTo(String value) {
            addCriterion("ncs_key =", value, "ncsKey");
            return (Criteria) this;
        }

        public Criteria andNcsKeyNotEqualTo(String value) {
            addCriterion("ncs_key <>", value, "ncsKey");
            return (Criteria) this;
        }

        public Criteria andNcsKeyGreaterThan(String value) {
            addCriterion("ncs_key >", value, "ncsKey");
            return (Criteria) this;
        }

        public Criteria andNcsKeyGreaterThanOrEqualTo(String value) {
            addCriterion("ncs_key >=", value, "ncsKey");
            return (Criteria) this;
        }

        public Criteria andNcsKeyLessThan(String value) {
            addCriterion("ncs_key <", value, "ncsKey");
            return (Criteria) this;
        }

        public Criteria andNcsKeyLessThanOrEqualTo(String value) {
            addCriterion("ncs_key <=", value, "ncsKey");
            return (Criteria) this;
        }

        public Criteria andNcsKeyLike(String value) {
            addCriterion("ncs_key like", value, "ncsKey");
            return (Criteria) this;
        }

        public Criteria andNcsKeyNotLike(String value) {
            addCriterion("ncs_key not like", value, "ncsKey");
            return (Criteria) this;
        }

        public Criteria andNcsKeyIn(List<String> values) {
            addCriterion("ncs_key in", values, "ncsKey");
            return (Criteria) this;
        }

        public Criteria andNcsKeyNotIn(List<String> values) {
            addCriterion("ncs_key not in", values, "ncsKey");
            return (Criteria) this;
        }

        public Criteria andNcsKeyBetween(String value1, String value2) {
            addCriterion("ncs_key between", value1, value2, "ncsKey");
            return (Criteria) this;
        }

        public Criteria andNcsKeyNotBetween(String value1, String value2) {
            addCriterion("ncs_key not between", value1, value2, "ncsKey");
            return (Criteria) this;
        }

        public Criteria andNcsNameIsNull() {
            addCriterion("ncs_name is null");
            return (Criteria) this;
        }

        public Criteria andNcsNameIsNotNull() {
            addCriterion("ncs_name is not null");
            return (Criteria) this;
        }

        public Criteria andNcsNameEqualTo(String value) {
            addCriterion("ncs_name =", value, "ncsName");
            return (Criteria) this;
        }

        public Criteria andNcsNameNotEqualTo(String value) {
            addCriterion("ncs_name <>", value, "ncsName");
            return (Criteria) this;
        }

        public Criteria andNcsNameGreaterThan(String value) {
            addCriterion("ncs_name >", value, "ncsName");
            return (Criteria) this;
        }

        public Criteria andNcsNameGreaterThanOrEqualTo(String value) {
            addCriterion("ncs_name >=", value, "ncsName");
            return (Criteria) this;
        }

        public Criteria andNcsNameLessThan(String value) {
            addCriterion("ncs_name <", value, "ncsName");
            return (Criteria) this;
        }

        public Criteria andNcsNameLessThanOrEqualTo(String value) {
            addCriterion("ncs_name <=", value, "ncsName");
            return (Criteria) this;
        }

        public Criteria andNcsNameLike(String value) {
            addCriterion("ncs_name like", value, "ncsName");
            return (Criteria) this;
        }

        public Criteria andNcsNameNotLike(String value) {
            addCriterion("ncs_name not like", value, "ncsName");
            return (Criteria) this;
        }

        public Criteria andNcsNameIn(List<String> values) {
            addCriterion("ncs_name in", values, "ncsName");
            return (Criteria) this;
        }

        public Criteria andNcsNameNotIn(List<String> values) {
            addCriterion("ncs_name not in", values, "ncsName");
            return (Criteria) this;
        }

        public Criteria andNcsNameBetween(String value1, String value2) {
            addCriterion("ncs_name between", value1, value2, "ncsName");
            return (Criteria) this;
        }

        public Criteria andNcsNameNotBetween(String value1, String value2) {
            addCriterion("ncs_name not between", value1, value2, "ncsName");
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