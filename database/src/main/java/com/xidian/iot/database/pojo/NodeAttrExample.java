package com.xidian.iot.database.pojo;

import java.util.ArrayList;
import java.util.List;

public class NodeAttrExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NodeAttrExample() {
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

        public Criteria andNaIdIsNull() {
            addCriterion("na_id is null");
            return (Criteria) this;
        }

        public Criteria andNaIdIsNotNull() {
            addCriterion("na_id is not null");
            return (Criteria) this;
        }

        public Criteria andNaIdEqualTo(Long value) {
            addCriterion("na_id =", value, "naId");
            return (Criteria) this;
        }

        public Criteria andNaIdNotEqualTo(Long value) {
            addCriterion("na_id <>", value, "naId");
            return (Criteria) this;
        }

        public Criteria andNaIdGreaterThan(Long value) {
            addCriterion("na_id >", value, "naId");
            return (Criteria) this;
        }

        public Criteria andNaIdGreaterThanOrEqualTo(Long value) {
            addCriterion("na_id >=", value, "naId");
            return (Criteria) this;
        }

        public Criteria andNaIdLessThan(Long value) {
            addCriterion("na_id <", value, "naId");
            return (Criteria) this;
        }

        public Criteria andNaIdLessThanOrEqualTo(Long value) {
            addCriterion("na_id <=", value, "naId");
            return (Criteria) this;
        }

        public Criteria andNaIdIn(List<Long> values) {
            addCriterion("na_id in", values, "naId");
            return (Criteria) this;
        }

        public Criteria andNaIdNotIn(List<Long> values) {
            addCriterion("na_id not in", values, "naId");
            return (Criteria) this;
        }

        public Criteria andNaIdBetween(Long value1, Long value2) {
            addCriterion("na_id between", value1, value2, "naId");
            return (Criteria) this;
        }

        public Criteria andNaIdNotBetween(Long value1, Long value2) {
            addCriterion("na_id not between", value1, value2, "naId");
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

        public Criteria andNaKeyIsNull() {
            addCriterion("na_key is null");
            return (Criteria) this;
        }

        public Criteria andNaKeyIsNotNull() {
            addCriterion("na_key is not null");
            return (Criteria) this;
        }

        public Criteria andNaKeyEqualTo(String value) {
            addCriterion("na_key =", value, "naKey");
            return (Criteria) this;
        }

        public Criteria andNaKeyNotEqualTo(String value) {
            addCriterion("na_key <>", value, "naKey");
            return (Criteria) this;
        }

        public Criteria andNaKeyGreaterThan(String value) {
            addCriterion("na_key >", value, "naKey");
            return (Criteria) this;
        }

        public Criteria andNaKeyGreaterThanOrEqualTo(String value) {
            addCriterion("na_key >=", value, "naKey");
            return (Criteria) this;
        }

        public Criteria andNaKeyLessThan(String value) {
            addCriterion("na_key <", value, "naKey");
            return (Criteria) this;
        }

        public Criteria andNaKeyLessThanOrEqualTo(String value) {
            addCriterion("na_key <=", value, "naKey");
            return (Criteria) this;
        }

        public Criteria andNaKeyLike(String value) {
            addCriterion("na_key like", value, "naKey");
            return (Criteria) this;
        }

        public Criteria andNaKeyNotLike(String value) {
            addCriterion("na_key not like", value, "naKey");
            return (Criteria) this;
        }

        public Criteria andNaKeyIn(List<String> values) {
            addCriterion("na_key in", values, "naKey");
            return (Criteria) this;
        }

        public Criteria andNaKeyNotIn(List<String> values) {
            addCriterion("na_key not in", values, "naKey");
            return (Criteria) this;
        }

        public Criteria andNaKeyBetween(String value1, String value2) {
            addCriterion("na_key between", value1, value2, "naKey");
            return (Criteria) this;
        }

        public Criteria andNaKeyNotBetween(String value1, String value2) {
            addCriterion("na_key not between", value1, value2, "naKey");
            return (Criteria) this;
        }

        public Criteria andNaNameIsNull() {
            addCriterion("na_name is null");
            return (Criteria) this;
        }

        public Criteria andNaNameIsNotNull() {
            addCriterion("na_name is not null");
            return (Criteria) this;
        }

        public Criteria andNaNameEqualTo(String value) {
            addCriterion("na_name =", value, "naName");
            return (Criteria) this;
        }

        public Criteria andNaNameNotEqualTo(String value) {
            addCriterion("na_name <>", value, "naName");
            return (Criteria) this;
        }

        public Criteria andNaNameGreaterThan(String value) {
            addCriterion("na_name >", value, "naName");
            return (Criteria) this;
        }

        public Criteria andNaNameGreaterThanOrEqualTo(String value) {
            addCriterion("na_name >=", value, "naName");
            return (Criteria) this;
        }

        public Criteria andNaNameLessThan(String value) {
            addCriterion("na_name <", value, "naName");
            return (Criteria) this;
        }

        public Criteria andNaNameLessThanOrEqualTo(String value) {
            addCriterion("na_name <=", value, "naName");
            return (Criteria) this;
        }

        public Criteria andNaNameLike(String value) {
            addCriterion("na_name like", value, "naName");
            return (Criteria) this;
        }

        public Criteria andNaNameNotLike(String value) {
            addCriterion("na_name not like", value, "naName");
            return (Criteria) this;
        }

        public Criteria andNaNameIn(List<String> values) {
            addCriterion("na_name in", values, "naName");
            return (Criteria) this;
        }

        public Criteria andNaNameNotIn(List<String> values) {
            addCriterion("na_name not in", values, "naName");
            return (Criteria) this;
        }

        public Criteria andNaNameBetween(String value1, String value2) {
            addCriterion("na_name between", value1, value2, "naName");
            return (Criteria) this;
        }

        public Criteria andNaNameNotBetween(String value1, String value2) {
            addCriterion("na_name not between", value1, value2, "naName");
            return (Criteria) this;
        }

        public Criteria andNaUnitIsNull() {
            addCriterion("na_unit is null");
            return (Criteria) this;
        }

        public Criteria andNaUnitIsNotNull() {
            addCriterion("na_unit is not null");
            return (Criteria) this;
        }

        public Criteria andNaUnitEqualTo(String value) {
            addCriterion("na_unit =", value, "naUnit");
            return (Criteria) this;
        }

        public Criteria andNaUnitNotEqualTo(String value) {
            addCriterion("na_unit <>", value, "naUnit");
            return (Criteria) this;
        }

        public Criteria andNaUnitGreaterThan(String value) {
            addCriterion("na_unit >", value, "naUnit");
            return (Criteria) this;
        }

        public Criteria andNaUnitGreaterThanOrEqualTo(String value) {
            addCriterion("na_unit >=", value, "naUnit");
            return (Criteria) this;
        }

        public Criteria andNaUnitLessThan(String value) {
            addCriterion("na_unit <", value, "naUnit");
            return (Criteria) this;
        }

        public Criteria andNaUnitLessThanOrEqualTo(String value) {
            addCriterion("na_unit <=", value, "naUnit");
            return (Criteria) this;
        }

        public Criteria andNaUnitLike(String value) {
            addCriterion("na_unit like", value, "naUnit");
            return (Criteria) this;
        }

        public Criteria andNaUnitNotLike(String value) {
            addCriterion("na_unit not like", value, "naUnit");
            return (Criteria) this;
        }

        public Criteria andNaUnitIn(List<String> values) {
            addCriterion("na_unit in", values, "naUnit");
            return (Criteria) this;
        }

        public Criteria andNaUnitNotIn(List<String> values) {
            addCriterion("na_unit not in", values, "naUnit");
            return (Criteria) this;
        }

        public Criteria andNaUnitBetween(String value1, String value2) {
            addCriterion("na_unit between", value1, value2, "naUnit");
            return (Criteria) this;
        }

        public Criteria andNaUnitNotBetween(String value1, String value2) {
            addCriterion("na_unit not between", value1, value2, "naUnit");
            return (Criteria) this;
        }

        public Criteria andNaSymIsNull() {
            addCriterion("na_sym is null");
            return (Criteria) this;
        }

        public Criteria andNaSymIsNotNull() {
            addCriterion("na_sym is not null");
            return (Criteria) this;
        }

        public Criteria andNaSymEqualTo(String value) {
            addCriterion("na_sym =", value, "naSym");
            return (Criteria) this;
        }

        public Criteria andNaSymNotEqualTo(String value) {
            addCriterion("na_sym <>", value, "naSym");
            return (Criteria) this;
        }

        public Criteria andNaSymGreaterThan(String value) {
            addCriterion("na_sym >", value, "naSym");
            return (Criteria) this;
        }

        public Criteria andNaSymGreaterThanOrEqualTo(String value) {
            addCriterion("na_sym >=", value, "naSym");
            return (Criteria) this;
        }

        public Criteria andNaSymLessThan(String value) {
            addCriterion("na_sym <", value, "naSym");
            return (Criteria) this;
        }

        public Criteria andNaSymLessThanOrEqualTo(String value) {
            addCriterion("na_sym <=", value, "naSym");
            return (Criteria) this;
        }

        public Criteria andNaSymLike(String value) {
            addCriterion("na_sym like", value, "naSym");
            return (Criteria) this;
        }

        public Criteria andNaSymNotLike(String value) {
            addCriterion("na_sym not like", value, "naSym");
            return (Criteria) this;
        }

        public Criteria andNaSymIn(List<String> values) {
            addCriterion("na_sym in", values, "naSym");
            return (Criteria) this;
        }

        public Criteria andNaSymNotIn(List<String> values) {
            addCriterion("na_sym not in", values, "naSym");
            return (Criteria) this;
        }

        public Criteria andNaSymBetween(String value1, String value2) {
            addCriterion("na_sym between", value1, value2, "naSym");
            return (Criteria) this;
        }

        public Criteria andNaSymNotBetween(String value1, String value2) {
            addCriterion("na_sym not between", value1, value2, "naSym");
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