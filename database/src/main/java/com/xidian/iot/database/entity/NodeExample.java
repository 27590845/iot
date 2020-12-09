package com.xidian.iot.database.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NodeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NodeExample() {
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

        public Criteria andNodeNameIsNull() {
            addCriterion("node_name is null");
            return (Criteria) this;
        }

        public Criteria andNodeNameIsNotNull() {
            addCriterion("node_name is not null");
            return (Criteria) this;
        }

        public Criteria andNodeNameEqualTo(String value) {
            addCriterion("node_name =", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotEqualTo(String value) {
            addCriterion("node_name <>", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameGreaterThan(String value) {
            addCriterion("node_name >", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameGreaterThanOrEqualTo(String value) {
            addCriterion("node_name >=", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLessThan(String value) {
            addCriterion("node_name <", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLessThanOrEqualTo(String value) {
            addCriterion("node_name <=", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLike(String value) {
            addCriterion("node_name like", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotLike(String value) {
            addCriterion("node_name not like", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameIn(List<String> values) {
            addCriterion("node_name in", values, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotIn(List<String> values) {
            addCriterion("node_name not in", values, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameBetween(String value1, String value2) {
            addCriterion("node_name between", value1, value2, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotBetween(String value1, String value2) {
            addCriterion("node_name not between", value1, value2, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeDescIsNull() {
            addCriterion("node_desc is null");
            return (Criteria) this;
        }

        public Criteria andNodeDescIsNotNull() {
            addCriterion("node_desc is not null");
            return (Criteria) this;
        }

        public Criteria andNodeDescEqualTo(String value) {
            addCriterion("node_desc =", value, "nodeDesc");
            return (Criteria) this;
        }

        public Criteria andNodeDescNotEqualTo(String value) {
            addCriterion("node_desc <>", value, "nodeDesc");
            return (Criteria) this;
        }

        public Criteria andNodeDescGreaterThan(String value) {
            addCriterion("node_desc >", value, "nodeDesc");
            return (Criteria) this;
        }

        public Criteria andNodeDescGreaterThanOrEqualTo(String value) {
            addCriterion("node_desc >=", value, "nodeDesc");
            return (Criteria) this;
        }

        public Criteria andNodeDescLessThan(String value) {
            addCriterion("node_desc <", value, "nodeDesc");
            return (Criteria) this;
        }

        public Criteria andNodeDescLessThanOrEqualTo(String value) {
            addCriterion("node_desc <=", value, "nodeDesc");
            return (Criteria) this;
        }

        public Criteria andNodeDescLike(String value) {
            addCriterion("node_desc like", value, "nodeDesc");
            return (Criteria) this;
        }

        public Criteria andNodeDescNotLike(String value) {
            addCriterion("node_desc not like", value, "nodeDesc");
            return (Criteria) this;
        }

        public Criteria andNodeDescIn(List<String> values) {
            addCriterion("node_desc in", values, "nodeDesc");
            return (Criteria) this;
        }

        public Criteria andNodeDescNotIn(List<String> values) {
            addCriterion("node_desc not in", values, "nodeDesc");
            return (Criteria) this;
        }

        public Criteria andNodeDescBetween(String value1, String value2) {
            addCriterion("node_desc between", value1, value2, "nodeDesc");
            return (Criteria) this;
        }

        public Criteria andNodeDescNotBetween(String value1, String value2) {
            addCriterion("node_desc not between", value1, value2, "nodeDesc");
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

        public Criteria andNodeMapIsNull() {
            addCriterion("node_map is null");
            return (Criteria) this;
        }

        public Criteria andNodeMapIsNotNull() {
            addCriterion("node_map is not null");
            return (Criteria) this;
        }

        public Criteria andNodeMapEqualTo(String value) {
            addCriterion("node_map =", value, "nodeMap");
            return (Criteria) this;
        }

        public Criteria andNodeMapNotEqualTo(String value) {
            addCriterion("node_map <>", value, "nodeMap");
            return (Criteria) this;
        }

        public Criteria andNodeMapGreaterThan(String value) {
            addCriterion("node_map >", value, "nodeMap");
            return (Criteria) this;
        }

        public Criteria andNodeMapGreaterThanOrEqualTo(String value) {
            addCriterion("node_map >=", value, "nodeMap");
            return (Criteria) this;
        }

        public Criteria andNodeMapLessThan(String value) {
            addCriterion("node_map <", value, "nodeMap");
            return (Criteria) this;
        }

        public Criteria andNodeMapLessThanOrEqualTo(String value) {
            addCriterion("node_map <=", value, "nodeMap");
            return (Criteria) this;
        }

        public Criteria andNodeMapLike(String value) {
            addCriterion("node_map like", value, "nodeMap");
            return (Criteria) this;
        }

        public Criteria andNodeMapNotLike(String value) {
            addCriterion("node_map not like", value, "nodeMap");
            return (Criteria) this;
        }

        public Criteria andNodeMapIn(List<String> values) {
            addCriterion("node_map in", values, "nodeMap");
            return (Criteria) this;
        }

        public Criteria andNodeMapNotIn(List<String> values) {
            addCriterion("node_map not in", values, "nodeMap");
            return (Criteria) this;
        }

        public Criteria andNodeMapBetween(String value1, String value2) {
            addCriterion("node_map between", value1, value2, "nodeMap");
            return (Criteria) this;
        }

        public Criteria andNodeMapNotBetween(String value1, String value2) {
            addCriterion("node_map not between", value1, value2, "nodeMap");
            return (Criteria) this;
        }

        public Criteria andNodeAttrnameIsNull() {
            addCriterion("node_attrname is null");
            return (Criteria) this;
        }

        public Criteria andNodeAttrnameIsNotNull() {
            addCriterion("node_attrname is not null");
            return (Criteria) this;
        }

        public Criteria andNodeAttrnameEqualTo(String value) {
            addCriterion("node_attrname =", value, "nodeAttrname");
            return (Criteria) this;
        }

        public Criteria andNodeAttrnameNotEqualTo(String value) {
            addCriterion("node_attrname <>", value, "nodeAttrname");
            return (Criteria) this;
        }

        public Criteria andNodeAttrnameGreaterThan(String value) {
            addCriterion("node_attrname >", value, "nodeAttrname");
            return (Criteria) this;
        }

        public Criteria andNodeAttrnameGreaterThanOrEqualTo(String value) {
            addCriterion("node_attrname >=", value, "nodeAttrname");
            return (Criteria) this;
        }

        public Criteria andNodeAttrnameLessThan(String value) {
            addCriterion("node_attrname <", value, "nodeAttrname");
            return (Criteria) this;
        }

        public Criteria andNodeAttrnameLessThanOrEqualTo(String value) {
            addCriterion("node_attrname <=", value, "nodeAttrname");
            return (Criteria) this;
        }

        public Criteria andNodeAttrnameLike(String value) {
            addCriterion("node_attrname like", value, "nodeAttrname");
            return (Criteria) this;
        }

        public Criteria andNodeAttrnameNotLike(String value) {
            addCriterion("node_attrname not like", value, "nodeAttrname");
            return (Criteria) this;
        }

        public Criteria andNodeAttrnameIn(List<String> values) {
            addCriterion("node_attrname in", values, "nodeAttrname");
            return (Criteria) this;
        }

        public Criteria andNodeAttrnameNotIn(List<String> values) {
            addCriterion("node_attrname not in", values, "nodeAttrname");
            return (Criteria) this;
        }

        public Criteria andNodeAttrnameBetween(String value1, String value2) {
            addCriterion("node_attrname between", value1, value2, "nodeAttrname");
            return (Criteria) this;
        }

        public Criteria andNodeAttrnameNotBetween(String value1, String value2) {
            addCriterion("node_attrname not between", value1, value2, "nodeAttrname");
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