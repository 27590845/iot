package com.xidian.iot.database.pojo;

import java.util.ArrayList;
import java.util.List;

public class NodeCondExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NodeCondExample() {
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

        public Criteria andSceneIdIsNull() {
            addCriterion("scene_id is null");
            return (Criteria) this;
        }

        public Criteria andSceneIdIsNotNull() {
            addCriterion("scene_id is not null");
            return (Criteria) this;
        }

        public Criteria andSceneIdEqualTo(Long value) {
            addCriterion("scene_id =", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdNotEqualTo(Long value) {
            addCriterion("scene_id <>", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdGreaterThan(Long value) {
            addCriterion("scene_id >", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdGreaterThanOrEqualTo(Long value) {
            addCriterion("scene_id >=", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdLessThan(Long value) {
            addCriterion("scene_id <", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdLessThanOrEqualTo(Long value) {
            addCriterion("scene_id <=", value, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdIn(List<Long> values) {
            addCriterion("scene_id in", values, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdNotIn(List<Long> values) {
            addCriterion("scene_id not in", values, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdBetween(Long value1, Long value2) {
            addCriterion("scene_id between", value1, value2, "sceneId");
            return (Criteria) this;
        }

        public Criteria andSceneIdNotBetween(Long value1, Long value2) {
            addCriterion("scene_id not between", value1, value2, "sceneId");
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

        public Criteria andNcOpIsNull() {
            addCriterion("nc_op is null");
            return (Criteria) this;
        }

        public Criteria andNcOpIsNotNull() {
            addCriterion("nc_op is not null");
            return (Criteria) this;
        }

        public Criteria andNcOpEqualTo(Byte value) {
            addCriterion("nc_op =", value, "ncOp");
            return (Criteria) this;
        }

        public Criteria andNcOpNotEqualTo(Byte value) {
            addCriterion("nc_op <>", value, "ncOp");
            return (Criteria) this;
        }

        public Criteria andNcOpGreaterThan(Byte value) {
            addCriterion("nc_op >", value, "ncOp");
            return (Criteria) this;
        }

        public Criteria andNcOpGreaterThanOrEqualTo(Byte value) {
            addCriterion("nc_op >=", value, "ncOp");
            return (Criteria) this;
        }

        public Criteria andNcOpLessThan(Byte value) {
            addCriterion("nc_op <", value, "ncOp");
            return (Criteria) this;
        }

        public Criteria andNcOpLessThanOrEqualTo(Byte value) {
            addCriterion("nc_op <=", value, "ncOp");
            return (Criteria) this;
        }

        public Criteria andNcOpIn(List<Byte> values) {
            addCriterion("nc_op in", values, "ncOp");
            return (Criteria) this;
        }

        public Criteria andNcOpNotIn(List<Byte> values) {
            addCriterion("nc_op not in", values, "ncOp");
            return (Criteria) this;
        }

        public Criteria andNcOpBetween(Byte value1, Byte value2) {
            addCriterion("nc_op between", value1, value2, "ncOp");
            return (Criteria) this;
        }

        public Criteria andNcOpNotBetween(Byte value1, Byte value2) {
            addCriterion("nc_op not between", value1, value2, "ncOp");
            return (Criteria) this;
        }

        public Criteria andNcValIsNull() {
            addCriterion("nc_val is null");
            return (Criteria) this;
        }

        public Criteria andNcValIsNotNull() {
            addCriterion("nc_val is not null");
            return (Criteria) this;
        }

        public Criteria andNcValEqualTo(String value) {
            addCriterion("nc_val =", value, "ncVal");
            return (Criteria) this;
        }

        public Criteria andNcValNotEqualTo(String value) {
            addCriterion("nc_val <>", value, "ncVal");
            return (Criteria) this;
        }

        public Criteria andNcValGreaterThan(String value) {
            addCriterion("nc_val >", value, "ncVal");
            return (Criteria) this;
        }

        public Criteria andNcValGreaterThanOrEqualTo(String value) {
            addCriterion("nc_val >=", value, "ncVal");
            return (Criteria) this;
        }

        public Criteria andNcValLessThan(String value) {
            addCriterion("nc_val <", value, "ncVal");
            return (Criteria) this;
        }

        public Criteria andNcValLessThanOrEqualTo(String value) {
            addCriterion("nc_val <=", value, "ncVal");
            return (Criteria) this;
        }

        public Criteria andNcValLike(String value) {
            addCriterion("nc_val like", value, "ncVal");
            return (Criteria) this;
        }

        public Criteria andNcValNotLike(String value) {
            addCriterion("nc_val not like", value, "ncVal");
            return (Criteria) this;
        }

        public Criteria andNcValIn(List<String> values) {
            addCriterion("nc_val in", values, "ncVal");
            return (Criteria) this;
        }

        public Criteria andNcValNotIn(List<String> values) {
            addCriterion("nc_val not in", values, "ncVal");
            return (Criteria) this;
        }

        public Criteria andNcValBetween(String value1, String value2) {
            addCriterion("nc_val between", value1, value2, "ncVal");
            return (Criteria) this;
        }

        public Criteria andNcValNotBetween(String value1, String value2) {
            addCriterion("nc_val not between", value1, value2, "ncVal");
            return (Criteria) this;
        }

        public Criteria andNodeSnIsNull() {
            addCriterion("node_sn is null");
            return (Criteria) this;
        }

        public Criteria andNodeSnIsNotNull() {
            addCriterion("node_sn is not null");
            return (Criteria) this;
        }

        public Criteria andNodeSnEqualTo(String value) {
            addCriterion("node_sn =", value, "nodeSn");
            return (Criteria) this;
        }

        public Criteria andNodeSnNotEqualTo(String value) {
            addCriterion("node_sn <>", value, "nodeSn");
            return (Criteria) this;
        }

        public Criteria andNodeSnGreaterThan(String value) {
            addCriterion("node_sn >", value, "nodeSn");
            return (Criteria) this;
        }

        public Criteria andNodeSnGreaterThanOrEqualTo(String value) {
            addCriterion("node_sn >=", value, "nodeSn");
            return (Criteria) this;
        }

        public Criteria andNodeSnLessThan(String value) {
            addCriterion("node_sn <", value, "nodeSn");
            return (Criteria) this;
        }

        public Criteria andNodeSnLessThanOrEqualTo(String value) {
            addCriterion("node_sn <=", value, "nodeSn");
            return (Criteria) this;
        }

        public Criteria andNodeSnLike(String value) {
            addCriterion("node_sn like", value, "nodeSn");
            return (Criteria) this;
        }

        public Criteria andNodeSnNotLike(String value) {
            addCriterion("node_sn not like", value, "nodeSn");
            return (Criteria) this;
        }

        public Criteria andNodeSnIn(List<String> values) {
            addCriterion("node_sn in", values, "nodeSn");
            return (Criteria) this;
        }

        public Criteria andNodeSnNotIn(List<String> values) {
            addCriterion("node_sn not in", values, "nodeSn");
            return (Criteria) this;
        }

        public Criteria andNodeSnBetween(String value1, String value2) {
            addCriterion("node_sn between", value1, value2, "nodeSn");
            return (Criteria) this;
        }

        public Criteria andNodeSnNotBetween(String value1, String value2) {
            addCriterion("node_sn not between", value1, value2, "nodeSn");
            return (Criteria) this;
        }

        public Criteria andSceneSnIsNull() {
            addCriterion("scene_sn is null");
            return (Criteria) this;
        }

        public Criteria andSceneSnIsNotNull() {
            addCriterion("scene_sn is not null");
            return (Criteria) this;
        }

        public Criteria andSceneSnEqualTo(String value) {
            addCriterion("scene_sn =", value, "sceneSn");
            return (Criteria) this;
        }

        public Criteria andSceneSnNotEqualTo(String value) {
            addCriterion("scene_sn <>", value, "sceneSn");
            return (Criteria) this;
        }

        public Criteria andSceneSnGreaterThan(String value) {
            addCriterion("scene_sn >", value, "sceneSn");
            return (Criteria) this;
        }

        public Criteria andSceneSnGreaterThanOrEqualTo(String value) {
            addCriterion("scene_sn >=", value, "sceneSn");
            return (Criteria) this;
        }

        public Criteria andSceneSnLessThan(String value) {
            addCriterion("scene_sn <", value, "sceneSn");
            return (Criteria) this;
        }

        public Criteria andSceneSnLessThanOrEqualTo(String value) {
            addCriterion("scene_sn <=", value, "sceneSn");
            return (Criteria) this;
        }

        public Criteria andSceneSnLike(String value) {
            addCriterion("scene_sn like", value, "sceneSn");
            return (Criteria) this;
        }

        public Criteria andSceneSnNotLike(String value) {
            addCriterion("scene_sn not like", value, "sceneSn");
            return (Criteria) this;
        }

        public Criteria andSceneSnIn(List<String> values) {
            addCriterion("scene_sn in", values, "sceneSn");
            return (Criteria) this;
        }

        public Criteria andSceneSnNotIn(List<String> values) {
            addCriterion("scene_sn not in", values, "sceneSn");
            return (Criteria) this;
        }

        public Criteria andSceneSnBetween(String value1, String value2) {
            addCriterion("scene_sn between", value1, value2, "sceneSn");
            return (Criteria) this;
        }

        public Criteria andSceneSnNotBetween(String value1, String value2) {
            addCriterion("scene_sn not between", value1, value2, "sceneSn");
            return (Criteria) this;
        }

        public Criteria andNcFitTimeIsNull() {
            addCriterion("nc_fit_time is null");
            return (Criteria) this;
        }

        public Criteria andNcFitTimeIsNotNull() {
            addCriterion("nc_fit_time is not null");
            return (Criteria) this;
        }

        public Criteria andNcFitTimeEqualTo(Integer value) {
            addCriterion("nc_fit_time =", value, "ncFitTime");
            return (Criteria) this;
        }

        public Criteria andNcFitTimeNotEqualTo(Integer value) {
            addCriterion("nc_fit_time <>", value, "ncFitTime");
            return (Criteria) this;
        }

        public Criteria andNcFitTimeGreaterThan(Integer value) {
            addCriterion("nc_fit_time >", value, "ncFitTime");
            return (Criteria) this;
        }

        public Criteria andNcFitTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("nc_fit_time >=", value, "ncFitTime");
            return (Criteria) this;
        }

        public Criteria andNcFitTimeLessThan(Integer value) {
            addCriterion("nc_fit_time <", value, "ncFitTime");
            return (Criteria) this;
        }

        public Criteria andNcFitTimeLessThanOrEqualTo(Integer value) {
            addCriterion("nc_fit_time <=", value, "ncFitTime");
            return (Criteria) this;
        }

        public Criteria andNcFitTimeIn(List<Integer> values) {
            addCriterion("nc_fit_time in", values, "ncFitTime");
            return (Criteria) this;
        }

        public Criteria andNcFitTimeNotIn(List<Integer> values) {
            addCriterion("nc_fit_time not in", values, "ncFitTime");
            return (Criteria) this;
        }

        public Criteria andNcFitTimeBetween(Integer value1, Integer value2) {
            addCriterion("nc_fit_time between", value1, value2, "ncFitTime");
            return (Criteria) this;
        }

        public Criteria andNcFitTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("nc_fit_time not between", value1, value2, "ncFitTime");
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