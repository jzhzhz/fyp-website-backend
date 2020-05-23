package com.fyp.websitebackend.csweb.domain;

import java.util.ArrayList;
import java.util.List;

public class ProfileCustomBlockExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProfileCustomBlockExample() {
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

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andDateBarIsNull() {
            addCriterion("date_bar is null");
            return (Criteria) this;
        }

        public Criteria andDateBarIsNotNull() {
            addCriterion("date_bar is not null");
            return (Criteria) this;
        }

        public Criteria andDateBarEqualTo(String value) {
            addCriterion("date_bar =", value, "dateBar");
            return (Criteria) this;
        }

        public Criteria andDateBarNotEqualTo(String value) {
            addCriterion("date_bar <>", value, "dateBar");
            return (Criteria) this;
        }

        public Criteria andDateBarGreaterThan(String value) {
            addCriterion("date_bar >", value, "dateBar");
            return (Criteria) this;
        }

        public Criteria andDateBarGreaterThanOrEqualTo(String value) {
            addCriterion("date_bar >=", value, "dateBar");
            return (Criteria) this;
        }

        public Criteria andDateBarLessThan(String value) {
            addCriterion("date_bar <", value, "dateBar");
            return (Criteria) this;
        }

        public Criteria andDateBarLessThanOrEqualTo(String value) {
            addCriterion("date_bar <=", value, "dateBar");
            return (Criteria) this;
        }

        public Criteria andDateBarLike(String value) {
            addCriterion("date_bar like", value, "dateBar");
            return (Criteria) this;
        }

        public Criteria andDateBarNotLike(String value) {
            addCriterion("date_bar not like", value, "dateBar");
            return (Criteria) this;
        }

        public Criteria andDateBarIn(List<String> values) {
            addCriterion("date_bar in", values, "dateBar");
            return (Criteria) this;
        }

        public Criteria andDateBarNotIn(List<String> values) {
            addCriterion("date_bar not in", values, "dateBar");
            return (Criteria) this;
        }

        public Criteria andDateBarBetween(String value1, String value2) {
            addCriterion("date_bar between", value1, value2, "dateBar");
            return (Criteria) this;
        }

        public Criteria andDateBarNotBetween(String value1, String value2) {
            addCriterion("date_bar not between", value1, value2, "dateBar");
            return (Criteria) this;
        }

        public Criteria andCodeSegmentIsNull() {
            addCriterion("code_segment is null");
            return (Criteria) this;
        }

        public Criteria andCodeSegmentIsNotNull() {
            addCriterion("code_segment is not null");
            return (Criteria) this;
        }

        public Criteria andCodeSegmentEqualTo(String value) {
            addCriterion("code_segment =", value, "codeSegment");
            return (Criteria) this;
        }

        public Criteria andCodeSegmentNotEqualTo(String value) {
            addCriterion("code_segment <>", value, "codeSegment");
            return (Criteria) this;
        }

        public Criteria andCodeSegmentGreaterThan(String value) {
            addCriterion("code_segment >", value, "codeSegment");
            return (Criteria) this;
        }

        public Criteria andCodeSegmentGreaterThanOrEqualTo(String value) {
            addCriterion("code_segment >=", value, "codeSegment");
            return (Criteria) this;
        }

        public Criteria andCodeSegmentLessThan(String value) {
            addCriterion("code_segment <", value, "codeSegment");
            return (Criteria) this;
        }

        public Criteria andCodeSegmentLessThanOrEqualTo(String value) {
            addCriterion("code_segment <=", value, "codeSegment");
            return (Criteria) this;
        }

        public Criteria andCodeSegmentLike(String value) {
            addCriterion("code_segment like", value, "codeSegment");
            return (Criteria) this;
        }

        public Criteria andCodeSegmentNotLike(String value) {
            addCriterion("code_segment not like", value, "codeSegment");
            return (Criteria) this;
        }

        public Criteria andCodeSegmentIn(List<String> values) {
            addCriterion("code_segment in", values, "codeSegment");
            return (Criteria) this;
        }

        public Criteria andCodeSegmentNotIn(List<String> values) {
            addCriterion("code_segment not in", values, "codeSegment");
            return (Criteria) this;
        }

        public Criteria andCodeSegmentBetween(String value1, String value2) {
            addCriterion("code_segment between", value1, value2, "codeSegment");
            return (Criteria) this;
        }

        public Criteria andCodeSegmentNotBetween(String value1, String value2) {
            addCriterion("code_segment not between", value1, value2, "codeSegment");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andDeprecatedIsNull() {
            addCriterion("deprecated is null");
            return (Criteria) this;
        }

        public Criteria andDeprecatedIsNotNull() {
            addCriterion("deprecated is not null");
            return (Criteria) this;
        }

        public Criteria andDeprecatedEqualTo(Integer value) {
            addCriterion("deprecated =", value, "deprecated");
            return (Criteria) this;
        }

        public Criteria andDeprecatedNotEqualTo(Integer value) {
            addCriterion("deprecated <>", value, "deprecated");
            return (Criteria) this;
        }

        public Criteria andDeprecatedGreaterThan(Integer value) {
            addCriterion("deprecated >", value, "deprecated");
            return (Criteria) this;
        }

        public Criteria andDeprecatedGreaterThanOrEqualTo(Integer value) {
            addCriterion("deprecated >=", value, "deprecated");
            return (Criteria) this;
        }

        public Criteria andDeprecatedLessThan(Integer value) {
            addCriterion("deprecated <", value, "deprecated");
            return (Criteria) this;
        }

        public Criteria andDeprecatedLessThanOrEqualTo(Integer value) {
            addCriterion("deprecated <=", value, "deprecated");
            return (Criteria) this;
        }

        public Criteria andDeprecatedIn(List<Integer> values) {
            addCriterion("deprecated in", values, "deprecated");
            return (Criteria) this;
        }

        public Criteria andDeprecatedNotIn(List<Integer> values) {
            addCriterion("deprecated not in", values, "deprecated");
            return (Criteria) this;
        }

        public Criteria andDeprecatedBetween(Integer value1, Integer value2) {
            addCriterion("deprecated between", value1, value2, "deprecated");
            return (Criteria) this;
        }

        public Criteria andDeprecatedNotBetween(Integer value1, Integer value2) {
            addCriterion("deprecated not between", value1, value2, "deprecated");
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