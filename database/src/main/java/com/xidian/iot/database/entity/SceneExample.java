package com.xidian.iot.database.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SceneExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SceneExample() {
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

        public Criteria andSceneNameIsNull() {
            addCriterion("scene_name is null");
            return (Criteria) this;
        }

        public Criteria andSceneNameIsNotNull() {
            addCriterion("scene_name is not null");
            return (Criteria) this;
        }

        public Criteria andSceneNameEqualTo(String value) {
            addCriterion("scene_name =", value, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameNotEqualTo(String value) {
            addCriterion("scene_name <>", value, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameGreaterThan(String value) {
            addCriterion("scene_name >", value, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameGreaterThanOrEqualTo(String value) {
            addCriterion("scene_name >=", value, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameLessThan(String value) {
            addCriterion("scene_name <", value, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameLessThanOrEqualTo(String value) {
            addCriterion("scene_name <=", value, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameLike(String value) {
            addCriterion("scene_name like", value, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameNotLike(String value) {
            addCriterion("scene_name not like", value, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameIn(List<String> values) {
            addCriterion("scene_name in", values, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameNotIn(List<String> values) {
            addCriterion("scene_name not in", values, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameBetween(String value1, String value2) {
            addCriterion("scene_name between", value1, value2, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneNameNotBetween(String value1, String value2) {
            addCriterion("scene_name not between", value1, value2, "sceneName");
            return (Criteria) this;
        }

        public Criteria andSceneLocIsNull() {
            addCriterion("scene_loc is null");
            return (Criteria) this;
        }

        public Criteria andSceneLocIsNotNull() {
            addCriterion("scene_loc is not null");
            return (Criteria) this;
        }

        public Criteria andSceneLocEqualTo(String value) {
            addCriterion("scene_loc =", value, "sceneLoc");
            return (Criteria) this;
        }

        public Criteria andSceneLocNotEqualTo(String value) {
            addCriterion("scene_loc <>", value, "sceneLoc");
            return (Criteria) this;
        }

        public Criteria andSceneLocGreaterThan(String value) {
            addCriterion("scene_loc >", value, "sceneLoc");
            return (Criteria) this;
        }

        public Criteria andSceneLocGreaterThanOrEqualTo(String value) {
            addCriterion("scene_loc >=", value, "sceneLoc");
            return (Criteria) this;
        }

        public Criteria andSceneLocLessThan(String value) {
            addCriterion("scene_loc <", value, "sceneLoc");
            return (Criteria) this;
        }

        public Criteria andSceneLocLessThanOrEqualTo(String value) {
            addCriterion("scene_loc <=", value, "sceneLoc");
            return (Criteria) this;
        }

        public Criteria andSceneLocLike(String value) {
            addCriterion("scene_loc like", value, "sceneLoc");
            return (Criteria) this;
        }

        public Criteria andSceneLocNotLike(String value) {
            addCriterion("scene_loc not like", value, "sceneLoc");
            return (Criteria) this;
        }

        public Criteria andSceneLocIn(List<String> values) {
            addCriterion("scene_loc in", values, "sceneLoc");
            return (Criteria) this;
        }

        public Criteria andSceneLocNotIn(List<String> values) {
            addCriterion("scene_loc not in", values, "sceneLoc");
            return (Criteria) this;
        }

        public Criteria andSceneLocBetween(String value1, String value2) {
            addCriterion("scene_loc between", value1, value2, "sceneLoc");
            return (Criteria) this;
        }

        public Criteria andSceneLocNotBetween(String value1, String value2) {
            addCriterion("scene_loc not between", value1, value2, "sceneLoc");
            return (Criteria) this;
        }

        public Criteria andSceneLngIsNull() {
            addCriterion("scene_lng is null");
            return (Criteria) this;
        }

        public Criteria andSceneLngIsNotNull() {
            addCriterion("scene_lng is not null");
            return (Criteria) this;
        }

        public Criteria andSceneLngEqualTo(Double value) {
            addCriterion("scene_lng =", value, "sceneLng");
            return (Criteria) this;
        }

        public Criteria andSceneLngNotEqualTo(Double value) {
            addCriterion("scene_lng <>", value, "sceneLng");
            return (Criteria) this;
        }

        public Criteria andSceneLngGreaterThan(Double value) {
            addCriterion("scene_lng >", value, "sceneLng");
            return (Criteria) this;
        }

        public Criteria andSceneLngGreaterThanOrEqualTo(Double value) {
            addCriterion("scene_lng >=", value, "sceneLng");
            return (Criteria) this;
        }

        public Criteria andSceneLngLessThan(Double value) {
            addCriterion("scene_lng <", value, "sceneLng");
            return (Criteria) this;
        }

        public Criteria andSceneLngLessThanOrEqualTo(Double value) {
            addCriterion("scene_lng <=", value, "sceneLng");
            return (Criteria) this;
        }

        public Criteria andSceneLngIn(List<Double> values) {
            addCriterion("scene_lng in", values, "sceneLng");
            return (Criteria) this;
        }

        public Criteria andSceneLngNotIn(List<Double> values) {
            addCriterion("scene_lng not in", values, "sceneLng");
            return (Criteria) this;
        }

        public Criteria andSceneLngBetween(Double value1, Double value2) {
            addCriterion("scene_lng between", value1, value2, "sceneLng");
            return (Criteria) this;
        }

        public Criteria andSceneLngNotBetween(Double value1, Double value2) {
            addCriterion("scene_lng not between", value1, value2, "sceneLng");
            return (Criteria) this;
        }

        public Criteria andSceneLatIsNull() {
            addCriterion("scene_lat is null");
            return (Criteria) this;
        }

        public Criteria andSceneLatIsNotNull() {
            addCriterion("scene_lat is not null");
            return (Criteria) this;
        }

        public Criteria andSceneLatEqualTo(Double value) {
            addCriterion("scene_lat =", value, "sceneLat");
            return (Criteria) this;
        }

        public Criteria andSceneLatNotEqualTo(Double value) {
            addCriterion("scene_lat <>", value, "sceneLat");
            return (Criteria) this;
        }

        public Criteria andSceneLatGreaterThan(Double value) {
            addCriterion("scene_lat >", value, "sceneLat");
            return (Criteria) this;
        }

        public Criteria andSceneLatGreaterThanOrEqualTo(Double value) {
            addCriterion("scene_lat >=", value, "sceneLat");
            return (Criteria) this;
        }

        public Criteria andSceneLatLessThan(Double value) {
            addCriterion("scene_lat <", value, "sceneLat");
            return (Criteria) this;
        }

        public Criteria andSceneLatLessThanOrEqualTo(Double value) {
            addCriterion("scene_lat <=", value, "sceneLat");
            return (Criteria) this;
        }

        public Criteria andSceneLatIn(List<Double> values) {
            addCriterion("scene_lat in", values, "sceneLat");
            return (Criteria) this;
        }

        public Criteria andSceneLatNotIn(List<Double> values) {
            addCriterion("scene_lat not in", values, "sceneLat");
            return (Criteria) this;
        }

        public Criteria andSceneLatBetween(Double value1, Double value2) {
            addCriterion("scene_lat between", value1, value2, "sceneLat");
            return (Criteria) this;
        }

        public Criteria andSceneLatNotBetween(Double value1, Double value2) {
            addCriterion("scene_lat not between", value1, value2, "sceneLat");
            return (Criteria) this;
        }

        public Criteria andSceneElIsNull() {
            addCriterion("scene_el is null");
            return (Criteria) this;
        }

        public Criteria andSceneElIsNotNull() {
            addCriterion("scene_el is not null");
            return (Criteria) this;
        }

        public Criteria andSceneElEqualTo(Double value) {
            addCriterion("scene_el =", value, "sceneEl");
            return (Criteria) this;
        }

        public Criteria andSceneElNotEqualTo(Double value) {
            addCriterion("scene_el <>", value, "sceneEl");
            return (Criteria) this;
        }

        public Criteria andSceneElGreaterThan(Double value) {
            addCriterion("scene_el >", value, "sceneEl");
            return (Criteria) this;
        }

        public Criteria andSceneElGreaterThanOrEqualTo(Double value) {
            addCriterion("scene_el >=", value, "sceneEl");
            return (Criteria) this;
        }

        public Criteria andSceneElLessThan(Double value) {
            addCriterion("scene_el <", value, "sceneEl");
            return (Criteria) this;
        }

        public Criteria andSceneElLessThanOrEqualTo(Double value) {
            addCriterion("scene_el <=", value, "sceneEl");
            return (Criteria) this;
        }

        public Criteria andSceneElIn(List<Double> values) {
            addCriterion("scene_el in", values, "sceneEl");
            return (Criteria) this;
        }

        public Criteria andSceneElNotIn(List<Double> values) {
            addCriterion("scene_el not in", values, "sceneEl");
            return (Criteria) this;
        }

        public Criteria andSceneElBetween(Double value1, Double value2) {
            addCriterion("scene_el between", value1, value2, "sceneEl");
            return (Criteria) this;
        }

        public Criteria andSceneElNotBetween(Double value1, Double value2) {
            addCriterion("scene_el not between", value1, value2, "sceneEl");
            return (Criteria) this;
        }

        public Criteria andSceneDescIsNull() {
            addCriterion("scene_desc is null");
            return (Criteria) this;
        }

        public Criteria andSceneDescIsNotNull() {
            addCriterion("scene_desc is not null");
            return (Criteria) this;
        }

        public Criteria andSceneDescEqualTo(String value) {
            addCriterion("scene_desc =", value, "sceneDesc");
            return (Criteria) this;
        }

        public Criteria andSceneDescNotEqualTo(String value) {
            addCriterion("scene_desc <>", value, "sceneDesc");
            return (Criteria) this;
        }

        public Criteria andSceneDescGreaterThan(String value) {
            addCriterion("scene_desc >", value, "sceneDesc");
            return (Criteria) this;
        }

        public Criteria andSceneDescGreaterThanOrEqualTo(String value) {
            addCriterion("scene_desc >=", value, "sceneDesc");
            return (Criteria) this;
        }

        public Criteria andSceneDescLessThan(String value) {
            addCriterion("scene_desc <", value, "sceneDesc");
            return (Criteria) this;
        }

        public Criteria andSceneDescLessThanOrEqualTo(String value) {
            addCriterion("scene_desc <=", value, "sceneDesc");
            return (Criteria) this;
        }

        public Criteria andSceneDescLike(String value) {
            addCriterion("scene_desc like", value, "sceneDesc");
            return (Criteria) this;
        }

        public Criteria andSceneDescNotLike(String value) {
            addCriterion("scene_desc not like", value, "sceneDesc");
            return (Criteria) this;
        }

        public Criteria andSceneDescIn(List<String> values) {
            addCriterion("scene_desc in", values, "sceneDesc");
            return (Criteria) this;
        }

        public Criteria andSceneDescNotIn(List<String> values) {
            addCriterion("scene_desc not in", values, "sceneDesc");
            return (Criteria) this;
        }

        public Criteria andSceneDescBetween(String value1, String value2) {
            addCriterion("scene_desc between", value1, value2, "sceneDesc");
            return (Criteria) this;
        }

        public Criteria andSceneDescNotBetween(String value1, String value2) {
            addCriterion("scene_desc not between", value1, value2, "sceneDesc");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andSceneIdentifierIsNull() {
            addCriterion("scene_identifier is null");
            return (Criteria) this;
        }

        public Criteria andSceneIdentifierIsNotNull() {
            addCriterion("scene_identifier is not null");
            return (Criteria) this;
        }

        public Criteria andSceneIdentifierEqualTo(String value) {
            addCriterion("scene_identifier =", value, "sceneIdentifier");
            return (Criteria) this;
        }

        public Criteria andSceneIdentifierNotEqualTo(String value) {
            addCriterion("scene_identifier <>", value, "sceneIdentifier");
            return (Criteria) this;
        }

        public Criteria andSceneIdentifierGreaterThan(String value) {
            addCriterion("scene_identifier >", value, "sceneIdentifier");
            return (Criteria) this;
        }

        public Criteria andSceneIdentifierGreaterThanOrEqualTo(String value) {
            addCriterion("scene_identifier >=", value, "sceneIdentifier");
            return (Criteria) this;
        }

        public Criteria andSceneIdentifierLessThan(String value) {
            addCriterion("scene_identifier <", value, "sceneIdentifier");
            return (Criteria) this;
        }

        public Criteria andSceneIdentifierLessThanOrEqualTo(String value) {
            addCriterion("scene_identifier <=", value, "sceneIdentifier");
            return (Criteria) this;
        }

        public Criteria andSceneIdentifierLike(String value) {
            addCriterion("scene_identifier like", value, "sceneIdentifier");
            return (Criteria) this;
        }

        public Criteria andSceneIdentifierNotLike(String value) {
            addCriterion("scene_identifier not like", value, "sceneIdentifier");
            return (Criteria) this;
        }

        public Criteria andSceneIdentifierIn(List<String> values) {
            addCriterion("scene_identifier in", values, "sceneIdentifier");
            return (Criteria) this;
        }

        public Criteria andSceneIdentifierNotIn(List<String> values) {
            addCriterion("scene_identifier not in", values, "sceneIdentifier");
            return (Criteria) this;
        }

        public Criteria andSceneIdentifierBetween(String value1, String value2) {
            addCriterion("scene_identifier between", value1, value2, "sceneIdentifier");
            return (Criteria) this;
        }

        public Criteria andSceneIdentifierNotBetween(String value1, String value2) {
            addCriterion("scene_identifier not between", value1, value2, "sceneIdentifier");
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