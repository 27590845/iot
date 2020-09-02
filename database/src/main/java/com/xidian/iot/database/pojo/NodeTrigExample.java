package com.xidian.iot.database.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NodeTrigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NodeTrigExample() {
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

        public Criteria andNtIdIsNull() {
            addCriterion("nt_id is null");
            return (Criteria) this;
        }

        public Criteria andNtIdIsNotNull() {
            addCriterion("nt_id is not null");
            return (Criteria) this;
        }

        public Criteria andNtIdEqualTo(Integer value) {
            addCriterion("nt_id =", value, "ntId");
            return (Criteria) this;
        }

        public Criteria andNtIdNotEqualTo(Integer value) {
            addCriterion("nt_id <>", value, "ntId");
            return (Criteria) this;
        }

        public Criteria andNtIdGreaterThan(Integer value) {
            addCriterion("nt_id >", value, "ntId");
            return (Criteria) this;
        }

        public Criteria andNtIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("nt_id >=", value, "ntId");
            return (Criteria) this;
        }

        public Criteria andNtIdLessThan(Integer value) {
            addCriterion("nt_id <", value, "ntId");
            return (Criteria) this;
        }

        public Criteria andNtIdLessThanOrEqualTo(Integer value) {
            addCriterion("nt_id <=", value, "ntId");
            return (Criteria) this;
        }

        public Criteria andNtIdIn(List<Integer> values) {
            addCriterion("nt_id in", values, "ntId");
            return (Criteria) this;
        }

        public Criteria andNtIdNotIn(List<Integer> values) {
            addCriterion("nt_id not in", values, "ntId");
            return (Criteria) this;
        }

        public Criteria andNtIdBetween(Integer value1, Integer value2) {
            addCriterion("nt_id between", value1, value2, "ntId");
            return (Criteria) this;
        }

        public Criteria andNtIdNotBetween(Integer value1, Integer value2) {
            addCriterion("nt_id not between", value1, value2, "ntId");
            return (Criteria) this;
        }

        public Criteria andNtNameIsNull() {
            addCriterion("nt_name is null");
            return (Criteria) this;
        }

        public Criteria andNtNameIsNotNull() {
            addCriterion("nt_name is not null");
            return (Criteria) this;
        }

        public Criteria andNtNameEqualTo(String value) {
            addCriterion("nt_name =", value, "ntName");
            return (Criteria) this;
        }

        public Criteria andNtNameNotEqualTo(String value) {
            addCriterion("nt_name <>", value, "ntName");
            return (Criteria) this;
        }

        public Criteria andNtNameGreaterThan(String value) {
            addCriterion("nt_name >", value, "ntName");
            return (Criteria) this;
        }

        public Criteria andNtNameGreaterThanOrEqualTo(String value) {
            addCriterion("nt_name >=", value, "ntName");
            return (Criteria) this;
        }

        public Criteria andNtNameLessThan(String value) {
            addCriterion("nt_name <", value, "ntName");
            return (Criteria) this;
        }

        public Criteria andNtNameLessThanOrEqualTo(String value) {
            addCriterion("nt_name <=", value, "ntName");
            return (Criteria) this;
        }

        public Criteria andNtNameLike(String value) {
            addCriterion("nt_name like", value, "ntName");
            return (Criteria) this;
        }

        public Criteria andNtNameNotLike(String value) {
            addCriterion("nt_name not like", value, "ntName");
            return (Criteria) this;
        }

        public Criteria andNtNameIn(List<String> values) {
            addCriterion("nt_name in", values, "ntName");
            return (Criteria) this;
        }

        public Criteria andNtNameNotIn(List<String> values) {
            addCriterion("nt_name not in", values, "ntName");
            return (Criteria) this;
        }

        public Criteria andNtNameBetween(String value1, String value2) {
            addCriterion("nt_name between", value1, value2, "ntName");
            return (Criteria) this;
        }

        public Criteria andNtNameNotBetween(String value1, String value2) {
            addCriterion("nt_name not between", value1, value2, "ntName");
            return (Criteria) this;
        }

        public Criteria andNtDescIsNull() {
            addCriterion("nt_desc is null");
            return (Criteria) this;
        }

        public Criteria andNtDescIsNotNull() {
            addCriterion("nt_desc is not null");
            return (Criteria) this;
        }

        public Criteria andNtDescEqualTo(String value) {
            addCriterion("nt_desc =", value, "ntDesc");
            return (Criteria) this;
        }

        public Criteria andNtDescNotEqualTo(String value) {
            addCriterion("nt_desc <>", value, "ntDesc");
            return (Criteria) this;
        }

        public Criteria andNtDescGreaterThan(String value) {
            addCriterion("nt_desc >", value, "ntDesc");
            return (Criteria) this;
        }

        public Criteria andNtDescGreaterThanOrEqualTo(String value) {
            addCriterion("nt_desc >=", value, "ntDesc");
            return (Criteria) this;
        }

        public Criteria andNtDescLessThan(String value) {
            addCriterion("nt_desc <", value, "ntDesc");
            return (Criteria) this;
        }

        public Criteria andNtDescLessThanOrEqualTo(String value) {
            addCriterion("nt_desc <=", value, "ntDesc");
            return (Criteria) this;
        }

        public Criteria andNtDescLike(String value) {
            addCriterion("nt_desc like", value, "ntDesc");
            return (Criteria) this;
        }

        public Criteria andNtDescNotLike(String value) {
            addCriterion("nt_desc not like", value, "ntDesc");
            return (Criteria) this;
        }

        public Criteria andNtDescIn(List<String> values) {
            addCriterion("nt_desc in", values, "ntDesc");
            return (Criteria) this;
        }

        public Criteria andNtDescNotIn(List<String> values) {
            addCriterion("nt_desc not in", values, "ntDesc");
            return (Criteria) this;
        }

        public Criteria andNtDescBetween(String value1, String value2) {
            addCriterion("nt_desc between", value1, value2, "ntDesc");
            return (Criteria) this;
        }

        public Criteria andNtDescNotBetween(String value1, String value2) {
            addCriterion("nt_desc not between", value1, value2, "ntDesc");
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

        public Criteria andNtInvlIsNull() {
            addCriterion("nt_invl is null");
            return (Criteria) this;
        }

        public Criteria andNtInvlIsNotNull() {
            addCriterion("nt_invl is not null");
            return (Criteria) this;
        }

        public Criteria andNtInvlEqualTo(Integer value) {
            addCriterion("nt_invl =", value, "ntInvl");
            return (Criteria) this;
        }

        public Criteria andNtInvlNotEqualTo(Integer value) {
            addCriterion("nt_invl <>", value, "ntInvl");
            return (Criteria) this;
        }

        public Criteria andNtInvlGreaterThan(Integer value) {
            addCriterion("nt_invl >", value, "ntInvl");
            return (Criteria) this;
        }

        public Criteria andNtInvlGreaterThanOrEqualTo(Integer value) {
            addCriterion("nt_invl >=", value, "ntInvl");
            return (Criteria) this;
        }

        public Criteria andNtInvlLessThan(Integer value) {
            addCriterion("nt_invl <", value, "ntInvl");
            return (Criteria) this;
        }

        public Criteria andNtInvlLessThanOrEqualTo(Integer value) {
            addCriterion("nt_invl <=", value, "ntInvl");
            return (Criteria) this;
        }

        public Criteria andNtInvlIn(List<Integer> values) {
            addCriterion("nt_invl in", values, "ntInvl");
            return (Criteria) this;
        }

        public Criteria andNtInvlNotIn(List<Integer> values) {
            addCriterion("nt_invl not in", values, "ntInvl");
            return (Criteria) this;
        }

        public Criteria andNtInvlBetween(Integer value1, Integer value2) {
            addCriterion("nt_invl between", value1, value2, "ntInvl");
            return (Criteria) this;
        }

        public Criteria andNtInvlNotBetween(Integer value1, Integer value2) {
            addCriterion("nt_invl not between", value1, value2, "ntInvl");
            return (Criteria) this;
        }

        public Criteria andNtReptIsNull() {
            addCriterion("nt_rept is null");
            return (Criteria) this;
        }

        public Criteria andNtReptIsNotNull() {
            addCriterion("nt_rept is not null");
            return (Criteria) this;
        }

        public Criteria andNtReptEqualTo(Byte value) {
            addCriterion("nt_rept =", value, "ntRept");
            return (Criteria) this;
        }

        public Criteria andNtReptNotEqualTo(Byte value) {
            addCriterion("nt_rept <>", value, "ntRept");
            return (Criteria) this;
        }

        public Criteria andNtReptGreaterThan(Byte value) {
            addCriterion("nt_rept >", value, "ntRept");
            return (Criteria) this;
        }

        public Criteria andNtReptGreaterThanOrEqualTo(Byte value) {
            addCriterion("nt_rept >=", value, "ntRept");
            return (Criteria) this;
        }

        public Criteria andNtReptLessThan(Byte value) {
            addCriterion("nt_rept <", value, "ntRept");
            return (Criteria) this;
        }

        public Criteria andNtReptLessThanOrEqualTo(Byte value) {
            addCriterion("nt_rept <=", value, "ntRept");
            return (Criteria) this;
        }

        public Criteria andNtReptIn(List<Byte> values) {
            addCriterion("nt_rept in", values, "ntRept");
            return (Criteria) this;
        }

        public Criteria andNtReptNotIn(List<Byte> values) {
            addCriterion("nt_rept not in", values, "ntRept");
            return (Criteria) this;
        }

        public Criteria andNtReptBetween(Byte value1, Byte value2) {
            addCriterion("nt_rept between", value1, value2, "ntRept");
            return (Criteria) this;
        }

        public Criteria andNtReptNotBetween(Byte value1, Byte value2) {
            addCriterion("nt_rept not between", value1, value2, "ntRept");
            return (Criteria) this;
        }

        public Criteria andNtExecIsNull() {
            addCriterion("nt_exec is null");
            return (Criteria) this;
        }

        public Criteria andNtExecIsNotNull() {
            addCriterion("nt_exec is not null");
            return (Criteria) this;
        }

        public Criteria andNtExecEqualTo(Byte value) {
            addCriterion("nt_exec =", value, "ntExec");
            return (Criteria) this;
        }

        public Criteria andNtExecNotEqualTo(Byte value) {
            addCriterion("nt_exec <>", value, "ntExec");
            return (Criteria) this;
        }

        public Criteria andNtExecGreaterThan(Byte value) {
            addCriterion("nt_exec >", value, "ntExec");
            return (Criteria) this;
        }

        public Criteria andNtExecGreaterThanOrEqualTo(Byte value) {
            addCriterion("nt_exec >=", value, "ntExec");
            return (Criteria) this;
        }

        public Criteria andNtExecLessThan(Byte value) {
            addCriterion("nt_exec <", value, "ntExec");
            return (Criteria) this;
        }

        public Criteria andNtExecLessThanOrEqualTo(Byte value) {
            addCriterion("nt_exec <=", value, "ntExec");
            return (Criteria) this;
        }

        public Criteria andNtExecIn(List<Byte> values) {
            addCriterion("nt_exec in", values, "ntExec");
            return (Criteria) this;
        }

        public Criteria andNtExecNotIn(List<Byte> values) {
            addCriterion("nt_exec not in", values, "ntExec");
            return (Criteria) this;
        }

        public Criteria andNtExecBetween(Byte value1, Byte value2) {
            addCriterion("nt_exec between", value1, value2, "ntExec");
            return (Criteria) this;
        }

        public Criteria andNtExecNotBetween(Byte value1, Byte value2) {
            addCriterion("nt_exec not between", value1, value2, "ntExec");
            return (Criteria) this;
        }

        public Criteria andNtExprIsNull() {
            addCriterion("nt_expr is null");
            return (Criteria) this;
        }

        public Criteria andNtExprIsNotNull() {
            addCriterion("nt_expr is not null");
            return (Criteria) this;
        }

        public Criteria andNtExprEqualTo(Date value) {
            addCriterion("nt_expr =", value, "ntExpr");
            return (Criteria) this;
        }

        public Criteria andNtExprNotEqualTo(Date value) {
            addCriterion("nt_expr <>", value, "ntExpr");
            return (Criteria) this;
        }

        public Criteria andNtExprGreaterThan(Date value) {
            addCriterion("nt_expr >", value, "ntExpr");
            return (Criteria) this;
        }

        public Criteria andNtExprGreaterThanOrEqualTo(Date value) {
            addCriterion("nt_expr >=", value, "ntExpr");
            return (Criteria) this;
        }

        public Criteria andNtExprLessThan(Date value) {
            addCriterion("nt_expr <", value, "ntExpr");
            return (Criteria) this;
        }

        public Criteria andNtExprLessThanOrEqualTo(Date value) {
            addCriterion("nt_expr <=", value, "ntExpr");
            return (Criteria) this;
        }

        public Criteria andNtExprIn(List<Date> values) {
            addCriterion("nt_expr in", values, "ntExpr");
            return (Criteria) this;
        }

        public Criteria andNtExprNotIn(List<Date> values) {
            addCriterion("nt_expr not in", values, "ntExpr");
            return (Criteria) this;
        }

        public Criteria andNtExprBetween(Date value1, Date value2) {
            addCriterion("nt_expr between", value1, value2, "ntExpr");
            return (Criteria) this;
        }

        public Criteria andNtExprNotBetween(Date value1, Date value2) {
            addCriterion("nt_expr not between", value1, value2, "ntExpr");
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