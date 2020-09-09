package com.xidian.iot.database.pojo;

import java.util.ArrayList;
import java.util.List;

public class NodeActAlertExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NodeActAlertExample() {
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

        public Criteria andNaaIdIsNull() {
            addCriterion("naa_id is null");
            return (Criteria) this;
        }

        public Criteria andNaaIdIsNotNull() {
            addCriterion("naa_id is not null");
            return (Criteria) this;
        }

        public Criteria andNaaIdEqualTo(Long value) {
            addCriterion("naa_id =", value, "naaId");
            return (Criteria) this;
        }

        public Criteria andNaaIdNotEqualTo(Long value) {
            addCriterion("naa_id <>", value, "naaId");
            return (Criteria) this;
        }

        public Criteria andNaaIdGreaterThan(Long value) {
            addCriterion("naa_id >", value, "naaId");
            return (Criteria) this;
        }

        public Criteria andNaaIdGreaterThanOrEqualTo(Long value) {
            addCriterion("naa_id >=", value, "naaId");
            return (Criteria) this;
        }

        public Criteria andNaaIdLessThan(Long value) {
            addCriterion("naa_id <", value, "naaId");
            return (Criteria) this;
        }

        public Criteria andNaaIdLessThanOrEqualTo(Long value) {
            addCriterion("naa_id <=", value, "naaId");
            return (Criteria) this;
        }

        public Criteria andNaaIdIn(List<Long> values) {
            addCriterion("naa_id in", values, "naaId");
            return (Criteria) this;
        }

        public Criteria andNaaIdNotIn(List<Long> values) {
            addCriterion("naa_id not in", values, "naaId");
            return (Criteria) this;
        }

        public Criteria andNaaIdBetween(Long value1, Long value2) {
            addCriterion("naa_id between", value1, value2, "naaId");
            return (Criteria) this;
        }

        public Criteria andNaaIdNotBetween(Long value1, Long value2) {
            addCriterion("naa_id not between", value1, value2, "naaId");
            return (Criteria) this;
        }

        public Criteria andNtIdIsNull() {
            addCriterion("nt_id is null");
            return (Criteria) this;
        }

        public Criteria andNtIdIsNotNull() {
            addCriterion("nt_id is not null");
            return (Criteria) this;
        }

        public Criteria andNtIdEqualTo(Long value) {
            addCriterion("nt_id =", value, "ntId");
            return (Criteria) this;
        }

        public Criteria andNtIdNotEqualTo(Long value) {
            addCriterion("nt_id <>", value, "ntId");
            return (Criteria) this;
        }

        public Criteria andNtIdGreaterThan(Long value) {
            addCriterion("nt_id >", value, "ntId");
            return (Criteria) this;
        }

        public Criteria andNtIdGreaterThanOrEqualTo(Long value) {
            addCriterion("nt_id >=", value, "ntId");
            return (Criteria) this;
        }

        public Criteria andNtIdLessThan(Long value) {
            addCriterion("nt_id <", value, "ntId");
            return (Criteria) this;
        }

        public Criteria andNtIdLessThanOrEqualTo(Long value) {
            addCriterion("nt_id <=", value, "ntId");
            return (Criteria) this;
        }

        public Criteria andNtIdIn(List<Long> values) {
            addCriterion("nt_id in", values, "ntId");
            return (Criteria) this;
        }

        public Criteria andNtIdNotIn(List<Long> values) {
            addCriterion("nt_id not in", values, "ntId");
            return (Criteria) this;
        }

        public Criteria andNtIdBetween(Long value1, Long value2) {
            addCriterion("nt_id between", value1, value2, "ntId");
            return (Criteria) this;
        }

        public Criteria andNtIdNotBetween(Long value1, Long value2) {
            addCriterion("nt_id not between", value1, value2, "ntId");
            return (Criteria) this;
        }

        public Criteria andNaaTypeIsNull() {
            addCriterion("naa_type is null");
            return (Criteria) this;
        }

        public Criteria andNaaTypeIsNotNull() {
            addCriterion("naa_type is not null");
            return (Criteria) this;
        }

        public Criteria andNaaTypeEqualTo(Byte value) {
            addCriterion("naa_type =", value, "naaType");
            return (Criteria) this;
        }

        public Criteria andNaaTypeNotEqualTo(Byte value) {
            addCriterion("naa_type <>", value, "naaType");
            return (Criteria) this;
        }

        public Criteria andNaaTypeGreaterThan(Byte value) {
            addCriterion("naa_type >", value, "naaType");
            return (Criteria) this;
        }

        public Criteria andNaaTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("naa_type >=", value, "naaType");
            return (Criteria) this;
        }

        public Criteria andNaaTypeLessThan(Byte value) {
            addCriterion("naa_type <", value, "naaType");
            return (Criteria) this;
        }

        public Criteria andNaaTypeLessThanOrEqualTo(Byte value) {
            addCriterion("naa_type <=", value, "naaType");
            return (Criteria) this;
        }

        public Criteria andNaaTypeIn(List<Byte> values) {
            addCriterion("naa_type in", values, "naaType");
            return (Criteria) this;
        }

        public Criteria andNaaTypeNotIn(List<Byte> values) {
            addCriterion("naa_type not in", values, "naaType");
            return (Criteria) this;
        }

        public Criteria andNaaTypeBetween(Byte value1, Byte value2) {
            addCriterion("naa_type between", value1, value2, "naaType");
            return (Criteria) this;
        }

        public Criteria andNaaTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("naa_type not between", value1, value2, "naaType");
            return (Criteria) this;
        }

        public Criteria andNaaValIsNull() {
            addCriterion("naa_val is null");
            return (Criteria) this;
        }

        public Criteria andNaaValIsNotNull() {
            addCriterion("naa_val is not null");
            return (Criteria) this;
        }

        public Criteria andNaaValEqualTo(String value) {
            addCriterion("naa_val =", value, "naaVal");
            return (Criteria) this;
        }

        public Criteria andNaaValNotEqualTo(String value) {
            addCriterion("naa_val <>", value, "naaVal");
            return (Criteria) this;
        }

        public Criteria andNaaValGreaterThan(String value) {
            addCriterion("naa_val >", value, "naaVal");
            return (Criteria) this;
        }

        public Criteria andNaaValGreaterThanOrEqualTo(String value) {
            addCriterion("naa_val >=", value, "naaVal");
            return (Criteria) this;
        }

        public Criteria andNaaValLessThan(String value) {
            addCriterion("naa_val <", value, "naaVal");
            return (Criteria) this;
        }

        public Criteria andNaaValLessThanOrEqualTo(String value) {
            addCriterion("naa_val <=", value, "naaVal");
            return (Criteria) this;
        }

        public Criteria andNaaValLike(String value) {
            addCriterion("naa_val like", value, "naaVal");
            return (Criteria) this;
        }

        public Criteria andNaaValNotLike(String value) {
            addCriterion("naa_val not like", value, "naaVal");
            return (Criteria) this;
        }

        public Criteria andNaaValIn(List<String> values) {
            addCriterion("naa_val in", values, "naaVal");
            return (Criteria) this;
        }

        public Criteria andNaaValNotIn(List<String> values) {
            addCriterion("naa_val not in", values, "naaVal");
            return (Criteria) this;
        }

        public Criteria andNaaValBetween(String value1, String value2) {
            addCriterion("naa_val between", value1, value2, "naaVal");
            return (Criteria) this;
        }

        public Criteria andNaaValNotBetween(String value1, String value2) {
            addCriterion("naa_val not between", value1, value2, "naaVal");
            return (Criteria) this;
        }

        public Criteria andNaaContentIsNull() {
            addCriterion("naa_content is null");
            return (Criteria) this;
        }

        public Criteria andNaaContentIsNotNull() {
            addCriterion("naa_content is not null");
            return (Criteria) this;
        }

        public Criteria andNaaContentEqualTo(String value) {
            addCriterion("naa_content =", value, "naaContent");
            return (Criteria) this;
        }

        public Criteria andNaaContentNotEqualTo(String value) {
            addCriterion("naa_content <>", value, "naaContent");
            return (Criteria) this;
        }

        public Criteria andNaaContentGreaterThan(String value) {
            addCriterion("naa_content >", value, "naaContent");
            return (Criteria) this;
        }

        public Criteria andNaaContentGreaterThanOrEqualTo(String value) {
            addCriterion("naa_content >=", value, "naaContent");
            return (Criteria) this;
        }

        public Criteria andNaaContentLessThan(String value) {
            addCriterion("naa_content <", value, "naaContent");
            return (Criteria) this;
        }

        public Criteria andNaaContentLessThanOrEqualTo(String value) {
            addCriterion("naa_content <=", value, "naaContent");
            return (Criteria) this;
        }

        public Criteria andNaaContentLike(String value) {
            addCriterion("naa_content like", value, "naaContent");
            return (Criteria) this;
        }

        public Criteria andNaaContentNotLike(String value) {
            addCriterion("naa_content not like", value, "naaContent");
            return (Criteria) this;
        }

        public Criteria andNaaContentIn(List<String> values) {
            addCriterion("naa_content in", values, "naaContent");
            return (Criteria) this;
        }

        public Criteria andNaaContentNotIn(List<String> values) {
            addCriterion("naa_content not in", values, "naaContent");
            return (Criteria) this;
        }

        public Criteria andNaaContentBetween(String value1, String value2) {
            addCriterion("naa_content between", value1, value2, "naaContent");
            return (Criteria) this;
        }

        public Criteria andNaaContentNotBetween(String value1, String value2) {
            addCriterion("naa_content not between", value1, value2, "naaContent");
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