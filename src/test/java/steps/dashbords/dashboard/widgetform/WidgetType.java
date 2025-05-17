package steps.dashbords.dashboard.widgetform;

public enum WidgetType {
    LAUNCH_STATISTICS_CHART("statisticTrend"),
    OVERALL_STATISTICS("overallStatistics"),
    LAUNCHES_DURATION_CHART("launchesDurationChart"),
    LAUNCH_EXECUTION_AND_ISSUE_STATISTIC("launchStatistics"),
    PROJECT_ACTIVITY_PANEL("activityStream"),
    TEST_CASES_GROWTH_TREND_CHART("casesTrend"),
    INVESTIGATED_PERCENTAGE_OF_LAUNCHES("investigatedTrend"),
    LAUNCHES_TABLE("launchesTable"),
    UNIQUE_BUGS_TABLE("uniqueBugTable"),
    MOST_FAILED_TEST_CASES_TABLE_TOP_50("topTestCases"),
    FAILED_CASES_TREND_CHART("bugTrend"),
    NON_PASSED_TEST_CASES_TREND_CHART("notPassed"),
    DIFFERENT_LAUNCHES_COMPARISON_CHART("launchesComparisonChart"),
    PASSING_RATE_PER_LAUNCH("passingRatePerLaunch"),
    PASSING_RATE_SUMMARY("passingRateSummary"),
    FLAKY_TEST_CASES_TABLE_TOP_50("flakyTestCases"),
    CUMULATIVE_TREND_CHART("cumulative"),
    MOST_POPULAR_PATTERN_TABLE_TOP_20("topPatternTemplates"),
    COMPONENT_HEALTH_CHECK("componentHealthCheck"),
    COMPONENT_HEALTH_CHECK_TABLE_VIEW("componentHealthCheckTable"),
    MOST_TIME_CONSUMING_TEST_CASES_WIDGET_TOP_20("mostTimeConsuming"),
    TEST_CASE_SEARCH("testCaseSearch");

    private String type;

    WidgetType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
