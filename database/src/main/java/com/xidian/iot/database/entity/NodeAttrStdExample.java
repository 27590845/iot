package com.xidian.iot.database.entity;

import java.util.ArrayList;
import java.util.List;

public class NodeAttrStdExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NodeAttrStdExample() {
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

        public Criteria andNasIdIsNull() {
            addCriterion("nas_id is null");
            return (Criteria) this;
        }

        public Criteria andNasIdIsNotNull() {
            addCriterion("nas_id is not null");
            return (Criteria) this;
        }

        public Criteria andNasIdEqualTo(Long value) {
            addCriterion("nas_id =", value, "nasId");
            return (Criteria) this;
        }

        public Criteria andNasIdNotEqualTo(Long value) {
            addCriterion("nas_id <>", value, "nasId");
            return (Criteria) this;
        }

        public Criteria andNasIdGreaterThan(Long value) {
            addCriterion("nas_id >", value, "nasId");
            return (Criteria) this;
        }

        public Criteria andNasIdGreaterThanOrEqualTo(Long value) {
            addCriterion("nas_id >=", value, "nasId");
            return (Criteria) this;
        }

        public Criteria andNasIdLessThan(Long value) {
            addCriterion("nas_id <", value, "nasId");
            return (Criteria) this;
        }

        public Criteria andNasIdLessThanOrEqualTo(Long value) {
            addCriterion("nas_id <=", value, "nasId");
            return (Criteria) this;
        }

        public Criteria andNasIdIn(List<Long> values) {
            addCriterion("nas_id in", values, "nasId");
            return (Criteria) this;
        }

        public Criteria andNasIdNotIn(List<Long> values) {
            addCriterion("nas_id not in", values, "nasId");
            return (Criteria) this;
        }

        public Criteria andNasIdBetween(Long value1, Long value2) {
            addCriterion("nas_id between", value1, value2, "nasId");
            return (Criteria) this;
        }

        public Criteria andNasIdNotBetween(Long value1, Long value2) {
            addCriterion("nas_id not between", value1, value2, "nasId");
            return (Criteria) this;
        }

        public Criteria andNasKeyIsNull() {
            addCriterion("nas_key is null");
            return (Criteria) this;
        }

        public Criteria andNasKeyIsNotNull() {
            addCriterion("nas_key is not null");
            return (Criteria) this;
        }

        public Criteria andNasKeyEqualTo(String value) {
            addCriterion("nas_key =", value, "nasKey");
            return (Criteria) this;
        }

        public Criteria andNasKeyNotEqualTo(String value) {
            addCriterion("nas_key <>", value, "nasKey");
            return (Criteria) this;
        }

        public Criteria andNasKeyGreaterThan(String value) {
            addCriterion("nas_key >", value, "nasKey");
            return (Criteria) this;
        }

        public Criteria andNasKeyGreaterThanOrEqualTo(String value) {
            addCriterion("nas_key >=", value, "nasKey");
            return (Criteria) this;
        }

        public Criteria andNasKeyLessThan(String value) {
            addCriterion("nas_key <", value, "nasKey");
            return (Criteria) this;
        }

        public Criteria andNasKeyLessThanOrEqualTo(String value) {
            addCriterion("nas_key <=", value, "nasKey");
            return (Criteria) this;
        }

        public Criteria andNasKeyLike(String value) {
            addCriterion("nas_key like", value, "nasKey");
            return (Criteria) this;
        }

        public Criteria andNasKeyNotLike(String value) {
            addCriterion("nas_key not like", value, "nasKey");
            return (Criteria) this;
        }

        public Criteria andNasKeyIn(List<String> values) {
            addCriterion("nas_key in", values, "nasKey");
            return (Criteria) this;
        }

        public Criteria andNasKeyNotIn(List<String> values) {
            addCriterion("nas_key not in", values, "nasKey");
            return (Criteria) this;
        }

        public Criteria andNasKeyBetween(String value1, String value2) {
            addCriterion("nas_key between", value1, value2, "nasKey");
            return (Criteria) this;
        }

        public Criteria andNasKeyNotBetween(String value1, String value2) {
            addCriterion("nas_key not between", value1, value2, "nasKey");
            return (Criteria) this;
        }

        public Criteria andNasDescIsNull() {
            addCriterion("nas_desc is null");
            return (Criteria) this;
        }

        public Criteria andNasDescIsNotNull() {
            addCriterion("nas_desc is not null");
            return (Criteria) this;
        }

        public Criteria andNasDescEqualTo(String value) {
            addCriterion("nas_desc =", value, "nasDesc");
            return (Criteria) this;
        }

        public Criteria andNasDescNotEqualTo(String value) {
            addCriterion("nas_desc <>", value, "nasDesc");
            return (Criteria) this;
        }

        public Criteria andNasDescGreaterThan(String value) {
            addCriterion("nas_desc >", value, "nasDesc");
            return (Criteria) this;
        }

        public Criteria andNasDescGreaterThanOrEqualTo(String value) {
            addCriterion("nas_desc >=", value, "nasDesc");
            return (Criteria) this;
        }

        public Criteria andNasDescLessThan(String value) {
            addCriterion("nas_desc <", value, "nasDesc");
            return (Criteria) this;
        }

        public Criteria andNasDescLessThanOrEqualTo(String value) {
            addCriterion("nas_desc <=", value, "nasDesc");
            return (Criteria) this;
        }

        public Criteria andNasDescLike(String value) {
            addCriterion("nas_desc like", value, "nasDesc");
            return (Criteria) this;
        }

        public Criteria andNasDescNotLike(String value) {
            addCriterion("nas_desc not like", value, "nasDesc");
            return (Criteria) this;
        }

        public Criteria andNasDescIn(List<String> values) {
            addCriterion("nas_desc in", values, "nasDesc");
            return (Criteria) this;
        }

        public Criteria andNasDescNotIn(List<String> values) {
            addCriterion("nas_desc not in", values, "nasDesc");
            return (Criteria) this;
        }

        public Criteria andNasDescBetween(String value1, String value2) {
            addCriterion("nas_desc between", value1, value2, "nasDesc");
            return (Criteria) this;
        }

        public Criteria andNasDescNotBetween(String value1, String value2) {
            addCriterion("nas_desc not between", value1, value2, "nasDesc");
            return (Criteria) this;
        }

        public Criteria andNasUnitIsNull() {
            addCriterion("nas_unit is null");
            return (Criteria) this;
        }

        public Criteria andNasUnitIsNotNull() {
            addCriterion("nas_unit is not null");
            return (Criteria) this;
        }

        public Criteria andNasUnitEqualTo(String value) {
            addCriterion("nas_unit =", value, "nasUnit");
            return (Criteria) this;
        }

        public Criteria andNasUnitNotEqualTo(String value) {
            addCriterion("nas_unit <>", value, "nasUnit");
            return (Criteria) this;
        }

        public Criteria andNasUnitGreaterThan(String value) {
            addCriterion("nas_unit >", value, "nasUnit");
            return (Criteria) this;
        }

        public Criteria andNasUnitGreaterThanOrEqualTo(String value) {
            addCriterion("nas_unit >=", value, "nasUnit");
            return (Criteria) this;
        }

        public Criteria andNasUnitLessThan(String value) {
            addCriterion("nas_unit <", value, "nasUnit");
            return (Criteria) this;
        }

        public Criteria andNasUnitLessThanOrEqualTo(String value) {
            addCriterion("nas_unit <=", value, "nasUnit");
            return (Criteria) this;
        }

        public Criteria andNasUnitLike(String value) {
            addCriterion("nas_unit like", value, "nasUnit");
            return (Criteria) this;
        }

        public Criteria andNasUnitNotLike(String value) {
            addCriterion("nas_unit not like", value, "nasUnit");
            return (Criteria) this;
        }

        public Criteria andNasUnitIn(List<String> values) {
            addCriterion("nas_unit in", values, "nasUnit");
            return (Criteria) this;
        }

        public Criteria andNasUnitNotIn(List<String> values) {
            addCriterion("nas_unit not in", values, "nasUnit");
            return (Criteria) this;
        }

        public Criteria andNasUnitBetween(String value1, String value2) {
            addCriterion("nas_unit between", value1, value2, "nasUnit");
            return (Criteria) this;
        }

        public Criteria andNasUnitNotBetween(String value1, String value2) {
            addCriterion("nas_unit not between", value1, value2, "nasUnit");
            return (Criteria) this;
        }

        public Criteria andNasSymIsNull() {
            addCriterion("nas_sym is null");
            return (Criteria) this;
        }

        public Criteria andNasSymIsNotNull() {
            addCriterion("nas_sym is not null");
            return (Criteria) this;
        }

        public Criteria andNasSymEqualTo(String value) {
            addCriterion("nas_sym =", value, "nasSym");
            return (Criteria) this;
        }

        public Criteria andNasSymNotEqualTo(String value) {
            addCriterion("nas_sym <>", value, "nasSym");
            return (Criteria) this;
        }

        public Criteria andNasSymGreaterThan(String value) {
            addCriterion("nas_sym >", value, "nasSym");
            return (Criteria) this;
        }

        public Criteria andNasSymGreaterThanOrEqualTo(String value) {
            addCriterion("nas_sym >=", value, "nasSym");
            return (Criteria) this;
        }

        public Criteria andNasSymLessThan(String value) {
            addCriterion("nas_sym <", value, "nasSym");
            return (Criteria) this;
        }

        public Criteria andNasSymLessThanOrEqualTo(String value) {
            addCriterion("nas_sym <=", value, "nasSym");
            return (Criteria) this;
        }

        public Criteria andNasSymLike(String value) {
            addCriterion("nas_sym like", value, "nasSym");
            return (Criteria) this;
        }

        public Criteria andNasSymNotLike(String value) {
            addCriterion("nas_sym not like", value, "nasSym");
            return (Criteria) this;
        }

        public Criteria andNasSymIn(List<String> values) {
            addCriterion("nas_sym in", values, "nasSym");
            return (Criteria) this;
        }

        public Criteria andNasSymNotIn(List<String> values) {
            addCriterion("nas_sym not in", values, "nasSym");
            return (Criteria) this;
        }

        public Criteria andNasSymBetween(String value1, String value2) {
            addCriterion("nas_sym between", value1, value2, "nasSym");
            return (Criteria) this;
        }

        public Criteria andNasSymNotBetween(String value1, String value2) {
            addCriterion("nas_sym not between", value1, value2, "nasSym");
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