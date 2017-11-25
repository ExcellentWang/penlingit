package com.ontheroad.mysql.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbWcalExample implements Serializable{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbWcalExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andHotWaterDeIsNull() {
            addCriterion("hot_water_de is null");
            return (Criteria) this;
        }

        public Criteria andHotWaterDeIsNotNull() {
            addCriterion("hot_water_de is not null");
            return (Criteria) this;
        }

        public Criteria andHotWaterDeEqualTo(String value) {
            addCriterion("hot_water_de =", value, "hotWaterDe");
            return (Criteria) this;
        }

        public Criteria andHotWaterDeNotEqualTo(String value) {
            addCriterion("hot_water_de <>", value, "hotWaterDe");
            return (Criteria) this;
        }

        public Criteria andHotWaterDeGreaterThan(String value) {
            addCriterion("hot_water_de >", value, "hotWaterDe");
            return (Criteria) this;
        }

        public Criteria andHotWaterDeGreaterThanOrEqualTo(String value) {
            addCriterion("hot_water_de >=", value, "hotWaterDe");
            return (Criteria) this;
        }

        public Criteria andHotWaterDeLessThan(String value) {
            addCriterion("hot_water_de <", value, "hotWaterDe");
            return (Criteria) this;
        }

        public Criteria andHotWaterDeLessThanOrEqualTo(String value) {
            addCriterion("hot_water_de <=", value, "hotWaterDe");
            return (Criteria) this;
        }

        public Criteria andHotWaterDeLike(String value) {
            addCriterion("hot_water_de like", value, "hotWaterDe");
            return (Criteria) this;
        }

        public Criteria andHotWaterDeNotLike(String value) {
            addCriterion("hot_water_de not like", value, "hotWaterDe");
            return (Criteria) this;
        }

        public Criteria andHotWaterDeIn(List<String> values) {
            addCriterion("hot_water_de in", values, "hotWaterDe");
            return (Criteria) this;
        }

        public Criteria andHotWaterDeNotIn(List<String> values) {
            addCriterion("hot_water_de not in", values, "hotWaterDe");
            return (Criteria) this;
        }

        public Criteria andHotWaterDeBetween(String value1, String value2) {
            addCriterion("hot_water_de between", value1, value2, "hotWaterDe");
            return (Criteria) this;
        }

        public Criteria andHotWaterDeNotBetween(String value1, String value2) {
            addCriterion("hot_water_de not between", value1, value2, "hotWaterDe");
            return (Criteria) this;
        }

        public Criteria andCodeWaterDeIsNull() {
            addCriterion("code_water_de is null");
            return (Criteria) this;
        }

        public Criteria andCodeWaterDeIsNotNull() {
            addCriterion("code_water_de is not null");
            return (Criteria) this;
        }

        public Criteria andCodeWaterDeEqualTo(String value) {
            addCriterion("code_water_de =", value, "codeWaterDe");
            return (Criteria) this;
        }

        public Criteria andCodeWaterDeNotEqualTo(String value) {
            addCriterion("code_water_de <>", value, "codeWaterDe");
            return (Criteria) this;
        }

        public Criteria andCodeWaterDeGreaterThan(String value) {
            addCriterion("code_water_de >", value, "codeWaterDe");
            return (Criteria) this;
        }

        public Criteria andCodeWaterDeGreaterThanOrEqualTo(String value) {
            addCriterion("code_water_de >=", value, "codeWaterDe");
            return (Criteria) this;
        }

        public Criteria andCodeWaterDeLessThan(String value) {
            addCriterion("code_water_de <", value, "codeWaterDe");
            return (Criteria) this;
        }

        public Criteria andCodeWaterDeLessThanOrEqualTo(String value) {
            addCriterion("code_water_de <=", value, "codeWaterDe");
            return (Criteria) this;
        }

        public Criteria andCodeWaterDeLike(String value) {
            addCriterion("code_water_de like", value, "codeWaterDe");
            return (Criteria) this;
        }

        public Criteria andCodeWaterDeNotLike(String value) {
            addCriterion("code_water_de not like", value, "codeWaterDe");
            return (Criteria) this;
        }

        public Criteria andCodeWaterDeIn(List<String> values) {
            addCriterion("code_water_de in", values, "codeWaterDe");
            return (Criteria) this;
        }

        public Criteria andCodeWaterDeNotIn(List<String> values) {
            addCriterion("code_water_de not in", values, "codeWaterDe");
            return (Criteria) this;
        }

        public Criteria andCodeWaterDeBetween(String value1, String value2) {
            addCriterion("code_water_de between", value1, value2, "codeWaterDe");
            return (Criteria) this;
        }

        public Criteria andCodeWaterDeNotBetween(String value1, String value2) {
            addCriterion("code_water_de not between", value1, value2, "codeWaterDe");
            return (Criteria) this;
        }

        public Criteria andCWaterDeIsNull() {
            addCriterion("c_water_de is null");
            return (Criteria) this;
        }

        public Criteria andCWaterDeIsNotNull() {
            addCriterion("c_water_de is not null");
            return (Criteria) this;
        }

        public Criteria andCWaterDeEqualTo(String value) {
            addCriterion("c_water_de =", value, "cWaterDe");
            return (Criteria) this;
        }

        public Criteria andCWaterDeNotEqualTo(String value) {
            addCriterion("c_water_de <>", value, "cWaterDe");
            return (Criteria) this;
        }

        public Criteria andCWaterDeGreaterThan(String value) {
            addCriterion("c_water_de >", value, "cWaterDe");
            return (Criteria) this;
        }

        public Criteria andCWaterDeGreaterThanOrEqualTo(String value) {
            addCriterion("c_water_de >=", value, "cWaterDe");
            return (Criteria) this;
        }

        public Criteria andCWaterDeLessThan(String value) {
            addCriterion("c_water_de <", value, "cWaterDe");
            return (Criteria) this;
        }

        public Criteria andCWaterDeLessThanOrEqualTo(String value) {
            addCriterion("c_water_de <=", value, "cWaterDe");
            return (Criteria) this;
        }

        public Criteria andCWaterDeLike(String value) {
            addCriterion("c_water_de like", value, "cWaterDe");
            return (Criteria) this;
        }

        public Criteria andCWaterDeNotLike(String value) {
            addCriterion("c_water_de not like", value, "cWaterDe");
            return (Criteria) this;
        }

        public Criteria andCWaterDeIn(List<String> values) {
            addCriterion("c_water_de in", values, "cWaterDe");
            return (Criteria) this;
        }

        public Criteria andCWaterDeNotIn(List<String> values) {
            addCriterion("c_water_de not in", values, "cWaterDe");
            return (Criteria) this;
        }

        public Criteria andCWaterDeBetween(String value1, String value2) {
            addCriterion("c_water_de between", value1, value2, "cWaterDe");
            return (Criteria) this;
        }

        public Criteria andCWaterDeNotBetween(String value1, String value2) {
            addCriterion("c_water_de not between", value1, value2, "cWaterDe");
            return (Criteria) this;
        }

        public Criteria andHWaterDeIsNull() {
            addCriterion("h_water_de is null");
            return (Criteria) this;
        }

        public Criteria andHWaterDeIsNotNull() {
            addCriterion("h_water_de is not null");
            return (Criteria) this;
        }

        public Criteria andHWaterDeEqualTo(String value) {
            addCriterion("h_water_de =", value, "hWaterDe");
            return (Criteria) this;
        }

        public Criteria andHWaterDeNotEqualTo(String value) {
            addCriterion("h_water_de <>", value, "hWaterDe");
            return (Criteria) this;
        }

        public Criteria andHWaterDeGreaterThan(String value) {
            addCriterion("h_water_de >", value, "hWaterDe");
            return (Criteria) this;
        }

        public Criteria andHWaterDeGreaterThanOrEqualTo(String value) {
            addCriterion("h_water_de >=", value, "hWaterDe");
            return (Criteria) this;
        }

        public Criteria andHWaterDeLessThan(String value) {
            addCriterion("h_water_de <", value, "hWaterDe");
            return (Criteria) this;
        }

        public Criteria andHWaterDeLessThanOrEqualTo(String value) {
            addCriterion("h_water_de <=", value, "hWaterDe");
            return (Criteria) this;
        }

        public Criteria andHWaterDeLike(String value) {
            addCriterion("h_water_de like", value, "hWaterDe");
            return (Criteria) this;
        }

        public Criteria andHWaterDeNotLike(String value) {
            addCriterion("h_water_de not like", value, "hWaterDe");
            return (Criteria) this;
        }

        public Criteria andHWaterDeIn(List<String> values) {
            addCriterion("h_water_de in", values, "hWaterDe");
            return (Criteria) this;
        }

        public Criteria andHWaterDeNotIn(List<String> values) {
            addCriterion("h_water_de not in", values, "hWaterDe");
            return (Criteria) this;
        }

        public Criteria andHWaterDeBetween(String value1, String value2) {
            addCriterion("h_water_de between", value1, value2, "hWaterDe");
            return (Criteria) this;
        }

        public Criteria andHWaterDeNotBetween(String value1, String value2) {
            addCriterion("h_water_de not between", value1, value2, "hWaterDe");
            return (Criteria) this;
        }

        public Criteria andMixingValveIsNull() {
            addCriterion("mixing_valve is null");
            return (Criteria) this;
        }

        public Criteria andMixingValveIsNotNull() {
            addCriterion("mixing_valve is not null");
            return (Criteria) this;
        }

        public Criteria andMixingValveEqualTo(String value) {
            addCriterion("mixing_valve =", value, "mixingValve");
            return (Criteria) this;
        }

        public Criteria andMixingValveNotEqualTo(String value) {
            addCriterion("mixing_valve <>", value, "mixingValve");
            return (Criteria) this;
        }

        public Criteria andMixingValveGreaterThan(String value) {
            addCriterion("mixing_valve >", value, "mixingValve");
            return (Criteria) this;
        }

        public Criteria andMixingValveGreaterThanOrEqualTo(String value) {
            addCriterion("mixing_valve >=", value, "mixingValve");
            return (Criteria) this;
        }

        public Criteria andMixingValveLessThan(String value) {
            addCriterion("mixing_valve <", value, "mixingValve");
            return (Criteria) this;
        }

        public Criteria andMixingValveLessThanOrEqualTo(String value) {
            addCriterion("mixing_valve <=", value, "mixingValve");
            return (Criteria) this;
        }

        public Criteria andMixingValveLike(String value) {
            addCriterion("mixing_valve like", value, "mixingValve");
            return (Criteria) this;
        }

        public Criteria andMixingValveNotLike(String value) {
            addCriterion("mixing_valve not like", value, "mixingValve");
            return (Criteria) this;
        }

        public Criteria andMixingValveIn(List<String> values) {
            addCriterion("mixing_valve in", values, "mixingValve");
            return (Criteria) this;
        }

        public Criteria andMixingValveNotIn(List<String> values) {
            addCriterion("mixing_valve not in", values, "mixingValve");
            return (Criteria) this;
        }

        public Criteria andMixingValveBetween(String value1, String value2) {
            addCriterion("mixing_valve between", value1, value2, "mixingValve");
            return (Criteria) this;
        }

        public Criteria andMixingValveNotBetween(String value1, String value2) {
            addCriterion("mixing_valve not between", value1, value2, "mixingValve");
            return (Criteria) this;
        }

        public Criteria andDischargeCoefficientIsNull() {
            addCriterion("discharge_coefficient is null");
            return (Criteria) this;
        }

        public Criteria andDischargeCoefficientIsNotNull() {
            addCriterion("discharge_coefficient is not null");
            return (Criteria) this;
        }

        public Criteria andDischargeCoefficientEqualTo(String value) {
            addCriterion("discharge_coefficient =", value, "dischargeCoefficient");
            return (Criteria) this;
        }

        public Criteria andDischargeCoefficientNotEqualTo(String value) {
            addCriterion("discharge_coefficient <>", value, "dischargeCoefficient");
            return (Criteria) this;
        }

        public Criteria andDischargeCoefficientGreaterThan(String value) {
            addCriterion("discharge_coefficient >", value, "dischargeCoefficient");
            return (Criteria) this;
        }

        public Criteria andDischargeCoefficientGreaterThanOrEqualTo(String value) {
            addCriterion("discharge_coefficient >=", value, "dischargeCoefficient");
            return (Criteria) this;
        }

        public Criteria andDischargeCoefficientLessThan(String value) {
            addCriterion("discharge_coefficient <", value, "dischargeCoefficient");
            return (Criteria) this;
        }

        public Criteria andDischargeCoefficientLessThanOrEqualTo(String value) {
            addCriterion("discharge_coefficient <=", value, "dischargeCoefficient");
            return (Criteria) this;
        }

        public Criteria andDischargeCoefficientLike(String value) {
            addCriterion("discharge_coefficient like", value, "dischargeCoefficient");
            return (Criteria) this;
        }

        public Criteria andDischargeCoefficientNotLike(String value) {
            addCriterion("discharge_coefficient not like", value, "dischargeCoefficient");
            return (Criteria) this;
        }

        public Criteria andDischargeCoefficientIn(List<String> values) {
            addCriterion("discharge_coefficient in", values, "dischargeCoefficient");
            return (Criteria) this;
        }

        public Criteria andDischargeCoefficientNotIn(List<String> values) {
            addCriterion("discharge_coefficient not in", values, "dischargeCoefficient");
            return (Criteria) this;
        }

        public Criteria andDischargeCoefficientBetween(String value1, String value2) {
            addCriterion("discharge_coefficient between", value1, value2, "dischargeCoefficient");
            return (Criteria) this;
        }

        public Criteria andDischargeCoefficientNotBetween(String value1, String value2) {
            addCriterion("discharge_coefficient not between", value1, value2, "dischargeCoefficient");
            return (Criteria) this;
        }

        public Criteria andTemperatureBValueIsNull() {
            addCriterion("temperature_b_value is null");
            return (Criteria) this;
        }

        public Criteria andTemperatureBValueIsNotNull() {
            addCriterion("temperature_b_value is not null");
            return (Criteria) this;
        }

        public Criteria andTemperatureBValueEqualTo(String value) {
            addCriterion("temperature_b_value =", value, "temperatureBValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureBValueNotEqualTo(String value) {
            addCriterion("temperature_b_value <>", value, "temperatureBValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureBValueGreaterThan(String value) {
            addCriterion("temperature_b_value >", value, "temperatureBValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureBValueGreaterThanOrEqualTo(String value) {
            addCriterion("temperature_b_value >=", value, "temperatureBValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureBValueLessThan(String value) {
            addCriterion("temperature_b_value <", value, "temperatureBValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureBValueLessThanOrEqualTo(String value) {
            addCriterion("temperature_b_value <=", value, "temperatureBValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureBValueLike(String value) {
            addCriterion("temperature_b_value like", value, "temperatureBValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureBValueNotLike(String value) {
            addCriterion("temperature_b_value not like", value, "temperatureBValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureBValueIn(List<String> values) {
            addCriterion("temperature_b_value in", values, "temperatureBValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureBValueNotIn(List<String> values) {
            addCriterion("temperature_b_value not in", values, "temperatureBValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureBValueBetween(String value1, String value2) {
            addCriterion("temperature_b_value between", value1, value2, "temperatureBValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureBValueNotBetween(String value1, String value2) {
            addCriterion("temperature_b_value not between", value1, value2, "temperatureBValue");
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

        public Criteria andDeviceNoIsNull() {
            addCriterion("device_no is null");
            return (Criteria) this;
        }

        public Criteria andDeviceNoIsNotNull() {
            addCriterion("device_no is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceNoEqualTo(String value) {
            addCriterion("device_no =", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoNotEqualTo(String value) {
            addCriterion("device_no <>", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoGreaterThan(String value) {
            addCriterion("device_no >", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoGreaterThanOrEqualTo(String value) {
            addCriterion("device_no >=", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoLessThan(String value) {
            addCriterion("device_no <", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoLessThanOrEqualTo(String value) {
            addCriterion("device_no <=", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoLike(String value) {
            addCriterion("device_no like", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoNotLike(String value) {
            addCriterion("device_no not like", value, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoIn(List<String> values) {
            addCriterion("device_no in", values, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoNotIn(List<String> values) {
            addCriterion("device_no not in", values, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoBetween(String value1, String value2) {
            addCriterion("device_no between", value1, value2, "deviceNo");
            return (Criteria) this;
        }

        public Criteria andDeviceNoNotBetween(String value1, String value2) {
            addCriterion("device_no not between", value1, value2, "deviceNo");
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