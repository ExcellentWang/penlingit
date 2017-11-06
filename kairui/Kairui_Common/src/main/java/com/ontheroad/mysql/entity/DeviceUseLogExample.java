package com.ontheroad.mysql.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceUseLogExample implements Serializable{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceUseLogExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdIsNull() {
            addCriterion("equipment_id is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdIsNotNull() {
            addCriterion("equipment_id is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdEqualTo(Integer value) {
            addCriterion("equipment_id =", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotEqualTo(Integer value) {
            addCriterion("equipment_id <>", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdGreaterThan(Integer value) {
            addCriterion("equipment_id >", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("equipment_id >=", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdLessThan(Integer value) {
            addCriterion("equipment_id <", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdLessThanOrEqualTo(Integer value) {
            addCriterion("equipment_id <=", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdIn(List<Integer> values) {
            addCriterion("equipment_id in", values, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotIn(List<Integer> values) {
            addCriterion("equipment_id not in", values, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdBetween(Integer value1, Integer value2) {
            addCriterion("equipment_id between", value1, value2, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("equipment_id not between", value1, value2, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andUploadstatusIsNull() {
            addCriterion("uploadStatus is null");
            return (Criteria) this;
        }

        public Criteria andUploadstatusIsNotNull() {
            addCriterion("uploadStatus is not null");
            return (Criteria) this;
        }

        public Criteria andUploadstatusEqualTo(String value) {
            addCriterion("uploadStatus =", value, "uploadstatus");
            return (Criteria) this;
        }

        public Criteria andUploadstatusNotEqualTo(String value) {
            addCriterion("uploadStatus <>", value, "uploadstatus");
            return (Criteria) this;
        }

        public Criteria andUploadstatusGreaterThan(String value) {
            addCriterion("uploadStatus >", value, "uploadstatus");
            return (Criteria) this;
        }

        public Criteria andUploadstatusGreaterThanOrEqualTo(String value) {
            addCriterion("uploadStatus >=", value, "uploadstatus");
            return (Criteria) this;
        }

        public Criteria andUploadstatusLessThan(String value) {
            addCriterion("uploadStatus <", value, "uploadstatus");
            return (Criteria) this;
        }

        public Criteria andUploadstatusLessThanOrEqualTo(String value) {
            addCriterion("uploadStatus <=", value, "uploadstatus");
            return (Criteria) this;
        }

        public Criteria andUploadstatusLike(String value) {
            addCriterion("uploadStatus like", value, "uploadstatus");
            return (Criteria) this;
        }

        public Criteria andUploadstatusNotLike(String value) {
            addCriterion("uploadStatus not like", value, "uploadstatus");
            return (Criteria) this;
        }

        public Criteria andUploadstatusIn(List<String> values) {
            addCriterion("uploadStatus in", values, "uploadstatus");
            return (Criteria) this;
        }

        public Criteria andUploadstatusNotIn(List<String> values) {
            addCriterion("uploadStatus not in", values, "uploadstatus");
            return (Criteria) this;
        }

        public Criteria andUploadstatusBetween(String value1, String value2) {
            addCriterion("uploadStatus between", value1, value2, "uploadstatus");
            return (Criteria) this;
        }

        public Criteria andUploadstatusNotBetween(String value1, String value2) {
            addCriterion("uploadStatus not between", value1, value2, "uploadstatus");
            return (Criteria) this;
        }

        public Criteria andUsetypeIsNull() {
            addCriterion("useType is null");
            return (Criteria) this;
        }

        public Criteria andUsetypeIsNotNull() {
            addCriterion("useType is not null");
            return (Criteria) this;
        }

        public Criteria andUsetypeEqualTo(String value) {
            addCriterion("useType =", value, "usetype");
            return (Criteria) this;
        }

        public Criteria andUsetypeNotEqualTo(String value) {
            addCriterion("useType <>", value, "usetype");
            return (Criteria) this;
        }

        public Criteria andUsetypeGreaterThan(String value) {
            addCriterion("useType >", value, "usetype");
            return (Criteria) this;
        }

        public Criteria andUsetypeGreaterThanOrEqualTo(String value) {
            addCriterion("useType >=", value, "usetype");
            return (Criteria) this;
        }

        public Criteria andUsetypeLessThan(String value) {
            addCriterion("useType <", value, "usetype");
            return (Criteria) this;
        }

        public Criteria andUsetypeLessThanOrEqualTo(String value) {
            addCriterion("useType <=", value, "usetype");
            return (Criteria) this;
        }

        public Criteria andUsetypeLike(String value) {
            addCriterion("useType like", value, "usetype");
            return (Criteria) this;
        }

        public Criteria andUsetypeNotLike(String value) {
            addCriterion("useType not like", value, "usetype");
            return (Criteria) this;
        }

        public Criteria andUsetypeIn(List<String> values) {
            addCriterion("useType in", values, "usetype");
            return (Criteria) this;
        }

        public Criteria andUsetypeNotIn(List<String> values) {
            addCriterion("useType not in", values, "usetype");
            return (Criteria) this;
        }

        public Criteria andUsetypeBetween(String value1, String value2) {
            addCriterion("useType between", value1, value2, "usetype");
            return (Criteria) this;
        }

        public Criteria andUsetypeNotBetween(String value1, String value2) {
            addCriterion("useType not between", value1, value2, "usetype");
            return (Criteria) this;
        }

        public Criteria andValveouttemperatureIsNull() {
            addCriterion("valveOutTemperature is null");
            return (Criteria) this;
        }

        public Criteria andValveouttemperatureIsNotNull() {
            addCriterion("valveOutTemperature is not null");
            return (Criteria) this;
        }

        public Criteria andValveouttemperatureEqualTo(String value) {
            addCriterion("valveOutTemperature =", value, "valveouttemperature");
            return (Criteria) this;
        }

        public Criteria andValveouttemperatureNotEqualTo(String value) {
            addCriterion("valveOutTemperature <>", value, "valveouttemperature");
            return (Criteria) this;
        }

        public Criteria andValveouttemperatureGreaterThan(String value) {
            addCriterion("valveOutTemperature >", value, "valveouttemperature");
            return (Criteria) this;
        }

        public Criteria andValveouttemperatureGreaterThanOrEqualTo(String value) {
            addCriterion("valveOutTemperature >=", value, "valveouttemperature");
            return (Criteria) this;
        }

        public Criteria andValveouttemperatureLessThan(String value) {
            addCriterion("valveOutTemperature <", value, "valveouttemperature");
            return (Criteria) this;
        }

        public Criteria andValveouttemperatureLessThanOrEqualTo(String value) {
            addCriterion("valveOutTemperature <=", value, "valveouttemperature");
            return (Criteria) this;
        }

        public Criteria andValveouttemperatureLike(String value) {
            addCriterion("valveOutTemperature like", value, "valveouttemperature");
            return (Criteria) this;
        }

        public Criteria andValveouttemperatureNotLike(String value) {
            addCriterion("valveOutTemperature not like", value, "valveouttemperature");
            return (Criteria) this;
        }

        public Criteria andValveouttemperatureIn(List<String> values) {
            addCriterion("valveOutTemperature in", values, "valveouttemperature");
            return (Criteria) this;
        }

        public Criteria andValveouttemperatureNotIn(List<String> values) {
            addCriterion("valveOutTemperature not in", values, "valveouttemperature");
            return (Criteria) this;
        }

        public Criteria andValveouttemperatureBetween(String value1, String value2) {
            addCriterion("valveOutTemperature between", value1, value2, "valveouttemperature");
            return (Criteria) this;
        }

        public Criteria andValveouttemperatureNotBetween(String value1, String value2) {
            addCriterion("valveOutTemperature not between", value1, value2, "valveouttemperature");
            return (Criteria) this;
        }

        public Criteria andSettemperatureIsNull() {
            addCriterion("setTemperature is null");
            return (Criteria) this;
        }

        public Criteria andSettemperatureIsNotNull() {
            addCriterion("setTemperature is not null");
            return (Criteria) this;
        }

        public Criteria andSettemperatureEqualTo(String value) {
            addCriterion("setTemperature =", value, "settemperature");
            return (Criteria) this;
        }

        public Criteria andSettemperatureNotEqualTo(String value) {
            addCriterion("setTemperature <>", value, "settemperature");
            return (Criteria) this;
        }

        public Criteria andSettemperatureGreaterThan(String value) {
            addCriterion("setTemperature >", value, "settemperature");
            return (Criteria) this;
        }

        public Criteria andSettemperatureGreaterThanOrEqualTo(String value) {
            addCriterion("setTemperature >=", value, "settemperature");
            return (Criteria) this;
        }

        public Criteria andSettemperatureLessThan(String value) {
            addCriterion("setTemperature <", value, "settemperature");
            return (Criteria) this;
        }

        public Criteria andSettemperatureLessThanOrEqualTo(String value) {
            addCriterion("setTemperature <=", value, "settemperature");
            return (Criteria) this;
        }

        public Criteria andSettemperatureLike(String value) {
            addCriterion("setTemperature like", value, "settemperature");
            return (Criteria) this;
        }

        public Criteria andSettemperatureNotLike(String value) {
            addCriterion("setTemperature not like", value, "settemperature");
            return (Criteria) this;
        }

        public Criteria andSettemperatureIn(List<String> values) {
            addCriterion("setTemperature in", values, "settemperature");
            return (Criteria) this;
        }

        public Criteria andSettemperatureNotIn(List<String> values) {
            addCriterion("setTemperature not in", values, "settemperature");
            return (Criteria) this;
        }

        public Criteria andSettemperatureBetween(String value1, String value2) {
            addCriterion("setTemperature between", value1, value2, "settemperature");
            return (Criteria) this;
        }

        public Criteria andSettemperatureNotBetween(String value1, String value2) {
            addCriterion("setTemperature not between", value1, value2, "settemperature");
            return (Criteria) this;
        }

        public Criteria andBuffertemperatureIsNull() {
            addCriterion("bufferTemperature is null");
            return (Criteria) this;
        }

        public Criteria andBuffertemperatureIsNotNull() {
            addCriterion("bufferTemperature is not null");
            return (Criteria) this;
        }

        public Criteria andBuffertemperatureEqualTo(String value) {
            addCriterion("bufferTemperature =", value, "buffertemperature");
            return (Criteria) this;
        }

        public Criteria andBuffertemperatureNotEqualTo(String value) {
            addCriterion("bufferTemperature <>", value, "buffertemperature");
            return (Criteria) this;
        }

        public Criteria andBuffertemperatureGreaterThan(String value) {
            addCriterion("bufferTemperature >", value, "buffertemperature");
            return (Criteria) this;
        }

        public Criteria andBuffertemperatureGreaterThanOrEqualTo(String value) {
            addCriterion("bufferTemperature >=", value, "buffertemperature");
            return (Criteria) this;
        }

        public Criteria andBuffertemperatureLessThan(String value) {
            addCriterion("bufferTemperature <", value, "buffertemperature");
            return (Criteria) this;
        }

        public Criteria andBuffertemperatureLessThanOrEqualTo(String value) {
            addCriterion("bufferTemperature <=", value, "buffertemperature");
            return (Criteria) this;
        }

        public Criteria andBuffertemperatureLike(String value) {
            addCriterion("bufferTemperature like", value, "buffertemperature");
            return (Criteria) this;
        }

        public Criteria andBuffertemperatureNotLike(String value) {
            addCriterion("bufferTemperature not like", value, "buffertemperature");
            return (Criteria) this;
        }

        public Criteria andBuffertemperatureIn(List<String> values) {
            addCriterion("bufferTemperature in", values, "buffertemperature");
            return (Criteria) this;
        }

        public Criteria andBuffertemperatureNotIn(List<String> values) {
            addCriterion("bufferTemperature not in", values, "buffertemperature");
            return (Criteria) this;
        }

        public Criteria andBuffertemperatureBetween(String value1, String value2) {
            addCriterion("bufferTemperature between", value1, value2, "buffertemperature");
            return (Criteria) this;
        }

        public Criteria andBuffertemperatureNotBetween(String value1, String value2) {
            addCriterion("bufferTemperature not between", value1, value2, "buffertemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureIsNull() {
            addCriterion("outTemperature is null");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureIsNotNull() {
            addCriterion("outTemperature is not null");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureEqualTo(String value) {
            addCriterion("outTemperature =", value, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureNotEqualTo(String value) {
            addCriterion("outTemperature <>", value, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureGreaterThan(String value) {
            addCriterion("outTemperature >", value, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureGreaterThanOrEqualTo(String value) {
            addCriterion("outTemperature >=", value, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureLessThan(String value) {
            addCriterion("outTemperature <", value, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureLessThanOrEqualTo(String value) {
            addCriterion("outTemperature <=", value, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureLike(String value) {
            addCriterion("outTemperature like", value, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureNotLike(String value) {
            addCriterion("outTemperature not like", value, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureIn(List<String> values) {
            addCriterion("outTemperature in", values, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureNotIn(List<String> values) {
            addCriterion("outTemperature not in", values, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureBetween(String value1, String value2) {
            addCriterion("outTemperature between", value1, value2, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureNotBetween(String value1, String value2) {
            addCriterion("outTemperature not between", value1, value2, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andFlowgradeIsNull() {
            addCriterion("flowGrade is null");
            return (Criteria) this;
        }

        public Criteria andFlowgradeIsNotNull() {
            addCriterion("flowGrade is not null");
            return (Criteria) this;
        }

        public Criteria andFlowgradeEqualTo(String value) {
            addCriterion("flowGrade =", value, "flowgrade");
            return (Criteria) this;
        }

        public Criteria andFlowgradeNotEqualTo(String value) {
            addCriterion("flowGrade <>", value, "flowgrade");
            return (Criteria) this;
        }

        public Criteria andFlowgradeGreaterThan(String value) {
            addCriterion("flowGrade >", value, "flowgrade");
            return (Criteria) this;
        }

        public Criteria andFlowgradeGreaterThanOrEqualTo(String value) {
            addCriterion("flowGrade >=", value, "flowgrade");
            return (Criteria) this;
        }

        public Criteria andFlowgradeLessThan(String value) {
            addCriterion("flowGrade <", value, "flowgrade");
            return (Criteria) this;
        }

        public Criteria andFlowgradeLessThanOrEqualTo(String value) {
            addCriterion("flowGrade <=", value, "flowgrade");
            return (Criteria) this;
        }

        public Criteria andFlowgradeLike(String value) {
            addCriterion("flowGrade like", value, "flowgrade");
            return (Criteria) this;
        }

        public Criteria andFlowgradeNotLike(String value) {
            addCriterion("flowGrade not like", value, "flowgrade");
            return (Criteria) this;
        }

        public Criteria andFlowgradeIn(List<String> values) {
            addCriterion("flowGrade in", values, "flowgrade");
            return (Criteria) this;
        }

        public Criteria andFlowgradeNotIn(List<String> values) {
            addCriterion("flowGrade not in", values, "flowgrade");
            return (Criteria) this;
        }

        public Criteria andFlowgradeBetween(String value1, String value2) {
            addCriterion("flowGrade between", value1, value2, "flowgrade");
            return (Criteria) this;
        }

        public Criteria andFlowgradeNotBetween(String value1, String value2) {
            addCriterion("flowGrade not between", value1, value2, "flowgrade");
            return (Criteria) this;
        }

        public Criteria andFlowspeedIsNull() {
            addCriterion("flowSpeed is null");
            return (Criteria) this;
        }

        public Criteria andFlowspeedIsNotNull() {
            addCriterion("flowSpeed is not null");
            return (Criteria) this;
        }

        public Criteria andFlowspeedEqualTo(String value) {
            addCriterion("flowSpeed =", value, "flowspeed");
            return (Criteria) this;
        }

        public Criteria andFlowspeedNotEqualTo(String value) {
            addCriterion("flowSpeed <>", value, "flowspeed");
            return (Criteria) this;
        }

        public Criteria andFlowspeedGreaterThan(String value) {
            addCriterion("flowSpeed >", value, "flowspeed");
            return (Criteria) this;
        }

        public Criteria andFlowspeedGreaterThanOrEqualTo(String value) {
            addCriterion("flowSpeed >=", value, "flowspeed");
            return (Criteria) this;
        }

        public Criteria andFlowspeedLessThan(String value) {
            addCriterion("flowSpeed <", value, "flowspeed");
            return (Criteria) this;
        }

        public Criteria andFlowspeedLessThanOrEqualTo(String value) {
            addCriterion("flowSpeed <=", value, "flowspeed");
            return (Criteria) this;
        }

        public Criteria andFlowspeedLike(String value) {
            addCriterion("flowSpeed like", value, "flowspeed");
            return (Criteria) this;
        }

        public Criteria andFlowspeedNotLike(String value) {
            addCriterion("flowSpeed not like", value, "flowspeed");
            return (Criteria) this;
        }

        public Criteria andFlowspeedIn(List<String> values) {
            addCriterion("flowSpeed in", values, "flowspeed");
            return (Criteria) this;
        }

        public Criteria andFlowspeedNotIn(List<String> values) {
            addCriterion("flowSpeed not in", values, "flowspeed");
            return (Criteria) this;
        }

        public Criteria andFlowspeedBetween(String value1, String value2) {
            addCriterion("flowSpeed between", value1, value2, "flowspeed");
            return (Criteria) this;
        }

        public Criteria andFlowspeedNotBetween(String value1, String value2) {
            addCriterion("flowSpeed not between", value1, value2, "flowspeed");
            return (Criteria) this;
        }

        public Criteria andBatteryvoltageIsNull() {
            addCriterion("batteryVoltage is null");
            return (Criteria) this;
        }

        public Criteria andBatteryvoltageIsNotNull() {
            addCriterion("batteryVoltage is not null");
            return (Criteria) this;
        }

        public Criteria andBatteryvoltageEqualTo(String value) {
            addCriterion("batteryVoltage =", value, "batteryvoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryvoltageNotEqualTo(String value) {
            addCriterion("batteryVoltage <>", value, "batteryvoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryvoltageGreaterThan(String value) {
            addCriterion("batteryVoltage >", value, "batteryvoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryvoltageGreaterThanOrEqualTo(String value) {
            addCriterion("batteryVoltage >=", value, "batteryvoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryvoltageLessThan(String value) {
            addCriterion("batteryVoltage <", value, "batteryvoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryvoltageLessThanOrEqualTo(String value) {
            addCriterion("batteryVoltage <=", value, "batteryvoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryvoltageLike(String value) {
            addCriterion("batteryVoltage like", value, "batteryvoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryvoltageNotLike(String value) {
            addCriterion("batteryVoltage not like", value, "batteryvoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryvoltageIn(List<String> values) {
            addCriterion("batteryVoltage in", values, "batteryvoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryvoltageNotIn(List<String> values) {
            addCriterion("batteryVoltage not in", values, "batteryvoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryvoltageBetween(String value1, String value2) {
            addCriterion("batteryVoltage between", value1, value2, "batteryvoltage");
            return (Criteria) this;
        }

        public Criteria andBatteryvoltageNotBetween(String value1, String value2) {
            addCriterion("batteryVoltage not between", value1, value2, "batteryvoltage");
            return (Criteria) this;
        }

        public Criteria andBatterytemperatureIsNull() {
            addCriterion("batteryTemperature is null");
            return (Criteria) this;
        }

        public Criteria andBatterytemperatureIsNotNull() {
            addCriterion("batteryTemperature is not null");
            return (Criteria) this;
        }

        public Criteria andBatterytemperatureEqualTo(String value) {
            addCriterion("batteryTemperature =", value, "batterytemperature");
            return (Criteria) this;
        }

        public Criteria andBatterytemperatureNotEqualTo(String value) {
            addCriterion("batteryTemperature <>", value, "batterytemperature");
            return (Criteria) this;
        }

        public Criteria andBatterytemperatureGreaterThan(String value) {
            addCriterion("batteryTemperature >", value, "batterytemperature");
            return (Criteria) this;
        }

        public Criteria andBatterytemperatureGreaterThanOrEqualTo(String value) {
            addCriterion("batteryTemperature >=", value, "batterytemperature");
            return (Criteria) this;
        }

        public Criteria andBatterytemperatureLessThan(String value) {
            addCriterion("batteryTemperature <", value, "batterytemperature");
            return (Criteria) this;
        }

        public Criteria andBatterytemperatureLessThanOrEqualTo(String value) {
            addCriterion("batteryTemperature <=", value, "batterytemperature");
            return (Criteria) this;
        }

        public Criteria andBatterytemperatureLike(String value) {
            addCriterion("batteryTemperature like", value, "batterytemperature");
            return (Criteria) this;
        }

        public Criteria andBatterytemperatureNotLike(String value) {
            addCriterion("batteryTemperature not like", value, "batterytemperature");
            return (Criteria) this;
        }

        public Criteria andBatterytemperatureIn(List<String> values) {
            addCriterion("batteryTemperature in", values, "batterytemperature");
            return (Criteria) this;
        }

        public Criteria andBatterytemperatureNotIn(List<String> values) {
            addCriterion("batteryTemperature not in", values, "batterytemperature");
            return (Criteria) this;
        }

        public Criteria andBatterytemperatureBetween(String value1, String value2) {
            addCriterion("batteryTemperature between", value1, value2, "batterytemperature");
            return (Criteria) this;
        }

        public Criteria andBatterytemperatureNotBetween(String value1, String value2) {
            addCriterion("batteryTemperature not between", value1, value2, "batterytemperature");
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

        public Criteria andSWorkStatusIsNull() {
            addCriterion("s_work_status is null");
            return (Criteria) this;
        }

        public Criteria andSWorkStatusIsNotNull() {
            addCriterion("s_work_status is not null");
            return (Criteria) this;
        }

        public Criteria andSWorkStatusEqualTo(String value) {
            addCriterion("s_work_status =", value, "sWorkStatus");
            return (Criteria) this;
        }

        public Criteria andSWorkStatusNotEqualTo(String value) {
            addCriterion("s_work_status <>", value, "sWorkStatus");
            return (Criteria) this;
        }

        public Criteria andSWorkStatusGreaterThan(String value) {
            addCriterion("s_work_status >", value, "sWorkStatus");
            return (Criteria) this;
        }

        public Criteria andSWorkStatusGreaterThanOrEqualTo(String value) {
            addCriterion("s_work_status >=", value, "sWorkStatus");
            return (Criteria) this;
        }

        public Criteria andSWorkStatusLessThan(String value) {
            addCriterion("s_work_status <", value, "sWorkStatus");
            return (Criteria) this;
        }

        public Criteria andSWorkStatusLessThanOrEqualTo(String value) {
            addCriterion("s_work_status <=", value, "sWorkStatus");
            return (Criteria) this;
        }

        public Criteria andSWorkStatusLike(String value) {
            addCriterion("s_work_status like", value, "sWorkStatus");
            return (Criteria) this;
        }

        public Criteria andSWorkStatusNotLike(String value) {
            addCriterion("s_work_status not like", value, "sWorkStatus");
            return (Criteria) this;
        }

        public Criteria andSWorkStatusIn(List<String> values) {
            addCriterion("s_work_status in", values, "sWorkStatus");
            return (Criteria) this;
        }

        public Criteria andSWorkStatusNotIn(List<String> values) {
            addCriterion("s_work_status not in", values, "sWorkStatus");
            return (Criteria) this;
        }

        public Criteria andSWorkStatusBetween(String value1, String value2) {
            addCriterion("s_work_status between", value1, value2, "sWorkStatus");
            return (Criteria) this;
        }

        public Criteria andSWorkStatusNotBetween(String value1, String value2) {
            addCriterion("s_work_status not between", value1, value2, "sWorkStatus");
            return (Criteria) this;
        }

        public Criteria andHotWaterTempIsNull() {
            addCriterion("hot_water_temp is null");
            return (Criteria) this;
        }

        public Criteria andHotWaterTempIsNotNull() {
            addCriterion("hot_water_temp is not null");
            return (Criteria) this;
        }

        public Criteria andHotWaterTempEqualTo(String value) {
            addCriterion("hot_water_temp =", value, "hotWaterTemp");
            return (Criteria) this;
        }

        public Criteria andHotWaterTempNotEqualTo(String value) {
            addCriterion("hot_water_temp <>", value, "hotWaterTemp");
            return (Criteria) this;
        }

        public Criteria andHotWaterTempGreaterThan(String value) {
            addCriterion("hot_water_temp >", value, "hotWaterTemp");
            return (Criteria) this;
        }

        public Criteria andHotWaterTempGreaterThanOrEqualTo(String value) {
            addCriterion("hot_water_temp >=", value, "hotWaterTemp");
            return (Criteria) this;
        }

        public Criteria andHotWaterTempLessThan(String value) {
            addCriterion("hot_water_temp <", value, "hotWaterTemp");
            return (Criteria) this;
        }

        public Criteria andHotWaterTempLessThanOrEqualTo(String value) {
            addCriterion("hot_water_temp <=", value, "hotWaterTemp");
            return (Criteria) this;
        }

        public Criteria andHotWaterTempLike(String value) {
            addCriterion("hot_water_temp like", value, "hotWaterTemp");
            return (Criteria) this;
        }

        public Criteria andHotWaterTempNotLike(String value) {
            addCriterion("hot_water_temp not like", value, "hotWaterTemp");
            return (Criteria) this;
        }

        public Criteria andHotWaterTempIn(List<String> values) {
            addCriterion("hot_water_temp in", values, "hotWaterTemp");
            return (Criteria) this;
        }

        public Criteria andHotWaterTempNotIn(List<String> values) {
            addCriterion("hot_water_temp not in", values, "hotWaterTemp");
            return (Criteria) this;
        }

        public Criteria andHotWaterTempBetween(String value1, String value2) {
            addCriterion("hot_water_temp between", value1, value2, "hotWaterTemp");
            return (Criteria) this;
        }

        public Criteria andHotWaterTempNotBetween(String value1, String value2) {
            addCriterion("hot_water_temp not between", value1, value2, "hotWaterTemp");
            return (Criteria) this;
        }

        public Criteria andCodeWaterTempIsNull() {
            addCriterion("code_water_temp is null");
            return (Criteria) this;
        }

        public Criteria andCodeWaterTempIsNotNull() {
            addCriterion("code_water_temp is not null");
            return (Criteria) this;
        }

        public Criteria andCodeWaterTempEqualTo(String value) {
            addCriterion("code_water_temp =", value, "codeWaterTemp");
            return (Criteria) this;
        }

        public Criteria andCodeWaterTempNotEqualTo(String value) {
            addCriterion("code_water_temp <>", value, "codeWaterTemp");
            return (Criteria) this;
        }

        public Criteria andCodeWaterTempGreaterThan(String value) {
            addCriterion("code_water_temp >", value, "codeWaterTemp");
            return (Criteria) this;
        }

        public Criteria andCodeWaterTempGreaterThanOrEqualTo(String value) {
            addCriterion("code_water_temp >=", value, "codeWaterTemp");
            return (Criteria) this;
        }

        public Criteria andCodeWaterTempLessThan(String value) {
            addCriterion("code_water_temp <", value, "codeWaterTemp");
            return (Criteria) this;
        }

        public Criteria andCodeWaterTempLessThanOrEqualTo(String value) {
            addCriterion("code_water_temp <=", value, "codeWaterTemp");
            return (Criteria) this;
        }

        public Criteria andCodeWaterTempLike(String value) {
            addCriterion("code_water_temp like", value, "codeWaterTemp");
            return (Criteria) this;
        }

        public Criteria andCodeWaterTempNotLike(String value) {
            addCriterion("code_water_temp not like", value, "codeWaterTemp");
            return (Criteria) this;
        }

        public Criteria andCodeWaterTempIn(List<String> values) {
            addCriterion("code_water_temp in", values, "codeWaterTemp");
            return (Criteria) this;
        }

        public Criteria andCodeWaterTempNotIn(List<String> values) {
            addCriterion("code_water_temp not in", values, "codeWaterTemp");
            return (Criteria) this;
        }

        public Criteria andCodeWaterTempBetween(String value1, String value2) {
            addCriterion("code_water_temp between", value1, value2, "codeWaterTemp");
            return (Criteria) this;
        }

        public Criteria andCodeWaterTempNotBetween(String value1, String value2) {
            addCriterion("code_water_temp not between", value1, value2, "codeWaterTemp");
            return (Criteria) this;
        }

        public Criteria andTimeTypeIsNull() {
            addCriterion("time_type is null");
            return (Criteria) this;
        }

        public Criteria andTimeTypeIsNotNull() {
            addCriterion("time_type is not null");
            return (Criteria) this;
        }

        public Criteria andTimeTypeEqualTo(String value) {
            addCriterion("time_type =", value, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeNotEqualTo(String value) {
            addCriterion("time_type <>", value, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeGreaterThan(String value) {
            addCriterion("time_type >", value, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("time_type >=", value, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeLessThan(String value) {
            addCriterion("time_type <", value, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeLessThanOrEqualTo(String value) {
            addCriterion("time_type <=", value, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeLike(String value) {
            addCriterion("time_type like", value, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeNotLike(String value) {
            addCriterion("time_type not like", value, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeIn(List<String> values) {
            addCriterion("time_type in", values, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeNotIn(List<String> values) {
            addCriterion("time_type not in", values, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeBetween(String value1, String value2) {
            addCriterion("time_type between", value1, value2, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeNotBetween(String value1, String value2) {
            addCriterion("time_type not between", value1, value2, "timeType");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable{

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable{
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