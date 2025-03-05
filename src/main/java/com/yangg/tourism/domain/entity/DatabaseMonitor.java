package com.yangg.tourism.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName database_monitor
 */
@TableName(value ="database_monitor")
@Data
public class DatabaseMonitor implements Serializable {
    /**
     * 
     */
    @TableField(value = "SCHEMA_NAME", select = true)
    private String schemaName;

    /**
     * 
     */
    @TableField(value = "DIGEST")
    private String digest;

    /**
     * 
     */
    @TableField(value = "DIGEST_TEXT")
    private String digestText;

    /**
     * 
     */
    @TableField(value = "COUNT_STAR")
    private Long countStar;

    /**
     * 
     */
    @TableField(value = "SUM_TIMER_WAIT")
    private Long sumTimerWait;

    /**
     * 
     */
    @TableField(value = "MIN_TIMER_WAIT")
    private Long minTimerWait;

    /**
     * 
     */
    @TableField(value = "AVG_TIMER_WAIT")
    private Long avgTimerWait;

    /**
     * 
     */
    @TableField(value = "MAX_TIMER_WAIT")
    private Long maxTimerWait;

    /**
     * 
     */
    @TableField(value = "SUM_LOCK_TIME")
    private Long sumLockTime;

    /**
     * 
     */
    @TableField(value = "SUM_ERRORS")
    private Long sumErrors;

    /**
     * 
     */
    @TableField(value = "SUM_WARNINGS")
    private Long sumWarnings;

    /**
     * 
     */
    @TableField(value = "SUM_ROWS_AFFECTED")
    private Long sumRowsAffected;

    /**
     * 
     */
    @TableField(value = "SUM_ROWS_SENT")
    private Long sumRowsSent;

    /**
     * 
     */
    @TableField(value = "SUM_ROWS_EXAMINED")
    private Long sumRowsExamined;

    /**
     * 
     */
    @TableField(value = "SUM_CREATED_TMP_DISK_TABLES")
    private Long sumCreatedTmpDiskTables;

    /**
     * 
     */
    @TableField(value = "SUM_CREATED_TMP_TABLES")
    private Long sumCreatedTmpTables;

    /**
     * 
     */
    @TableField(value = "SUM_SELECT_FULL_JOIN")
    private Long sumSelectFullJoin;

    /**
     * 
     */
    @TableField(value = "SUM_SELECT_FULL_RANGE_JOIN")
    private Long sumSelectFullRangeJoin;

    /**
     * 
     */
    @TableField(value = "SUM_SELECT_RANGE")
    private Long sumSelectRange;

    /**
     * 
     */
    @TableField(value = "SUM_SELECT_RANGE_CHECK")
    private Long sumSelectRangeCheck;

    /**
     * 
     */
    @TableField(value = "SUM_SELECT_SCAN")
    private Long SUM_SELECT_SCAN;

    /**
     * 
     */
    @TableField(value = "SUM_SORT_MERGE_PASSES")
    private Long SUM_SORT_MERGE_PASSES;

    /**
     * 
     */
    @TableField(value = "SUM_SORT_RANGE")
    private Long sumSortRange;

    /**
     * 
     */
    @TableField(value = "SUM_SORT_ROWS")
    private Long sumSortRows;

    /**
     * 
     */
    @TableField(value = "SUM_SORT_SCAN")
    private Long sumSortScan;

    /**
     * 
     */
    @TableField(value = "SUM_NO_INDEX_USED")
    private Long sumNoIndexUsed;

    /**
     * 
     */
    @TableField(value = "SUM_NO_GOOD_INDEX_USED")
    private Long sumNoGoodIndexUsed;

    /**
     * 
     */
    @TableField(value = "SUM_CPU_TIME")
    private Long sumCpuTime;

    /**
     * 
     */
    @TableField(value = "MAX_CONTROLLED_MEMORY")
    private Long maxControlledMemory;

    /**
     * 
     */
    @TableField(value = "MAX_TOTAL_MEMORY")
    private Long maxTotalMemory;

    /**
     * 
     */
    @TableField(value = "COUNT_SECONDARY")
    private Long countSecondary;

    /**
     * 
     */
    @TableField(value = "FIRST_SEEN")
    private Date firstSeen;

    /**
     * 
     */
    @TableField(value = "LAST_SEEN")
    private Date lastSeen;

    /**
     * 
     */
    @TableField(value = "QUANTILE_95")
    private Long quantile95;

    /**
     * 
     */
    @TableField(value = "QUANTILE_99")
    private Long quantile99;

    /**
     * 
     */
    @TableField(value = "QUANTILE_999")
    private Long quantile999;

    /**
     * 
     */
    @TableField(value = "QUERY_SAMPLE_TEXT")
    private String querySampleText;

    /**
     * 
     */
    @TableField(value = "QUERY_SAMPLE_SEEN")
    private Date querySampleSeen;

    /**
     * 
     */
    @TableField(value = "QUERY_SAMPLE_TIMER_WAIT")
    private Long querySampleTimerWait;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DatabaseMonitor other = (DatabaseMonitor) that;
        return (this.getSchemaName() == null ? other.getSchemaName() == null : this.getSchemaName().equals(other.getSchemaName()))
            && (this.getDigest() == null ? other.getDigest() == null : this.getDigest().equals(other.getDigest()))
            && (this.getDigestText() == null ? other.getDigestText() == null : this.getDigestText().equals(other.getDigestText()))
            && (this.getCountStar() == null ? other.getCountStar() == null : this.getCountStar().equals(other.getCountStar()))
            && (this.getSumTimerWait() == null ? other.getSumTimerWait() == null : this.getSumTimerWait().equals(other.getSumTimerWait()))
            && (this.getMinTimerWait() == null ? other.getMinTimerWait() == null : this.getMinTimerWait().equals(other.getMinTimerWait()))
            && (this.getAvgTimerWait() == null ? other.getAvgTimerWait() == null : this.getAvgTimerWait().equals(other.getAvgTimerWait()))
            && (this.getMaxTimerWait() == null ? other.getMaxTimerWait() == null : this.getMaxTimerWait().equals(other.getMaxTimerWait()))
            && (this.getSumLockTime() == null ? other.getSumLockTime() == null : this.getSumLockTime().equals(other.getSumLockTime()))
            && (this.getSumErrors() == null ? other.getSumErrors() == null : this.getSumErrors().equals(other.getSumErrors()))
            && (this.getSumWarnings() == null ? other.getSumWarnings() == null : this.getSumWarnings().equals(other.getSumWarnings()))
            && (this.getSumRowsAffected() == null ? other.getSumRowsAffected() == null : this.getSumRowsAffected().equals(other.getSumRowsAffected()))
            && (this.getSumRowsSent() == null ? other.getSumRowsSent() == null : this.getSumRowsSent().equals(other.getSumRowsSent()))
            && (this.getSumRowsExamined() == null ? other.getSumRowsExamined() == null : this.getSumRowsExamined().equals(other.getSumRowsExamined()))
            && (this.getSumCreatedTmpDiskTables() == null ? other.getSumCreatedTmpDiskTables() == null : this.getSumCreatedTmpDiskTables().equals(other.getSumCreatedTmpDiskTables()))
            && (this.getSumCreatedTmpTables() == null ? other.getSumCreatedTmpTables() == null : this.getSumCreatedTmpTables().equals(other.getSumCreatedTmpTables()))
            && (this.getSumSelectFullJoin() == null ? other.getSumSelectFullJoin() == null : this.getSumSelectFullJoin().equals(other.getSumSelectFullJoin()))
            && (this.getSumSelectFullRangeJoin() == null ? other.getSumSelectFullRangeJoin() == null : this.getSumSelectFullRangeJoin().equals(other.getSumSelectFullRangeJoin()))
            && (this.getSumSelectRange() == null ? other.getSumSelectRange() == null : this.getSumSelectRange().equals(other.getSumSelectRange()))
            && (this.getSumSelectRangeCheck() == null ? other.getSumSelectRangeCheck() == null : this.getSumSelectRangeCheck().equals(other.getSumSelectRangeCheck()))
            && (this.getSUM_SELECT_SCAN() == null ? other.getSUM_SELECT_SCAN() == null : this.getSUM_SELECT_SCAN().equals(other.getSUM_SELECT_SCAN()))
            && (this.getSUM_SORT_MERGE_PASSES() == null ? other.getSUM_SORT_MERGE_PASSES() == null : this.getSUM_SORT_MERGE_PASSES().equals(other.getSUM_SORT_MERGE_PASSES()))
            && (this.getSumSortRange() == null ? other.getSumSortRange() == null : this.getSumSortRange().equals(other.getSumSortRange()))
            && (this.getSumSortRows() == null ? other.getSumSortRows() == null : this.getSumSortRows().equals(other.getSumSortRows()))
            && (this.getSumSortScan() == null ? other.getSumSortScan() == null : this.getSumSortScan().equals(other.getSumSortScan()))
            && (this.getSumNoIndexUsed() == null ? other.getSumNoIndexUsed() == null : this.getSumNoIndexUsed().equals(other.getSumNoIndexUsed()))
            && (this.getSumNoGoodIndexUsed() == null ? other.getSumNoGoodIndexUsed() == null : this.getSumNoGoodIndexUsed().equals(other.getSumNoGoodIndexUsed()))
            && (this.getSumCpuTime() == null ? other.getSumCpuTime() == null : this.getSumCpuTime().equals(other.getSumCpuTime()))
            && (this.getMaxControlledMemory() == null ? other.getMaxControlledMemory() == null : this.getMaxControlledMemory().equals(other.getMaxControlledMemory()))
            && (this.getMaxTotalMemory() == null ? other.getMaxTotalMemory() == null : this.getMaxTotalMemory().equals(other.getMaxTotalMemory()))
            && (this.getCountSecondary() == null ? other.getCountSecondary() == null : this.getCountSecondary().equals(other.getCountSecondary()))
            && (this.getFirstSeen() == null ? other.getFirstSeen() == null : this.getFirstSeen().equals(other.getFirstSeen()))
            && (this.getLastSeen() == null ? other.getLastSeen() == null : this.getLastSeen().equals(other.getLastSeen()))
            && (this.getQuantile95() == null ? other.getQuantile95() == null : this.getQuantile95().equals(other.getQuantile95()))
            && (this.getQuantile99() == null ? other.getQuantile99() == null : this.getQuantile99().equals(other.getQuantile99()))
            && (this.getQuantile999() == null ? other.getQuantile999() == null : this.getQuantile999().equals(other.getQuantile999()))
            && (this.getQuerySampleText() == null ? other.getQuerySampleText() == null : this.getQuerySampleText().equals(other.getQuerySampleText()))
            && (this.getQuerySampleSeen() == null ? other.getQuerySampleSeen() == null : this.getQuerySampleSeen().equals(other.getQuerySampleSeen()))
            && (this.getQuerySampleTimerWait() == null ? other.getQuerySampleTimerWait() == null : this.getQuerySampleTimerWait().equals(other.getQuerySampleTimerWait()));
    }

//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((getSchemaName() == null) ? 0 : getSchemaName().hashCode());
//        result = prime * result + ((getDigest() == null) ? 0 : getDigest().hashCode());
//        result = prime * result + ((getDigestText() == null) ? 0 : getDigestText().hashCode());
//        result = prime * result + ((getCountStar() == null) ? 0 : getCountStar().hashCode());
//        result = prime * result + ((getSumTimerWait() == null) ? 0 : getSumTimerWait().hashCode());
//        result = prime * result + ((getMinTimerWait() == null) ? 0 : getMinTimerWait().hashCode());
//        result = prime * result + ((getAvgTimerWait() == null) ? 0 : getAvgTimerWait().hashCode());
//        result = prime * result + ((getMaxTimerWait() == null) ? 0 : getMaxTimerWait().hashCode());
//        result = prime * result + ((getSumLockTime() == null) ? 0 : getSumLockTime().hashCode());
//        result = prime * result + ((getSumErrors() == null) ? 0 : getSumErrors().hashCode());
//        result = prime * result + ((getSumWarnings() == null) ? 0 : getSumWarnings().hashCode());
//        result = prime * result + ((getSumRowsAffected() == null) ? 0 : getSumRowsAffected().hashCode());
//        result = prime * result + ((getSumRowsSent() == null) ? 0 : getSumRowsSent().hashCode());
//        result = prime * result + ((getSumRowsExamined() == null) ? 0 : getSumRowsExamined().hashCode());
//        result = prime * result + ((getSumCreatedTmpDiskTables() == null) ? 0 : getSumCreatedTmpDiskTables().hashCode());
//        result = prime * result + ((getSumCreatedTmpTables() == null) ? 0 : getSumCreatedTmpTables().hashCode());
//        result = prime * result + ((getSumSelectFullJoin() == null) ? 0 : getSumSelectFullJoin().hashCode());
//        result = prime * result + ((getSumSelectFullRangeJoin() == null) ? 0 : getSumSelectFullRangeJoin().hashCode());
//        result = prime * result + ((getSumSelectRange() == null) ? 0 : getSumSelectRange().hashCode());
//        result = prime * result + ((getSumSelectRangeCheck() == null) ? 0 : getSumSelectRangeCheck().hashCode());
//        result = prime * result + ((getSUM_SELECT_SCAN() == null) ? 0 : getSUM_SELECT_SCAN().hashCode());
//        result = prime * result + ((getSUM_SORT_MERGE_PASSES() == null) ? 0 : getSUM_SORT_MERGE_PASSES().hashCode());
//        result = prime * result + ((getSumSortRange() == null) ? 0 : getSumSortRange().hashCode());
//        result = prime * result + ((getSUM_SORT_ROWS() == null) ? 0 : getSUM_SORT_ROWS().hashCode());
//        result = prime * result + ((getSUM_SORT_SCAN() == null) ? 0 : getSUM_SORT_SCAN().hashCode());
//        result = prime * result + ((getSUM_NO_INDEX_USED() == null) ? 0 : getSUM_NO_INDEX_USED().hashCode());
//        result = prime * result + ((getSUM_NO_GOOD_INDEX_USED() == null) ? 0 : getSUM_NO_GOOD_INDEX_USED().hashCode());
//        result = prime * result + ((getSUM_CPU_TIME() == null) ? 0 : getSUM_CPU_TIME().hashCode());
//        result = prime * result + ((getMAX_CONTROLLED_MEMORY() == null) ? 0 : getMAX_CONTROLLED_MEMORY().hashCode());
//        result = prime * result + ((getMAX_TOTAL_MEMORY() == null) ? 0 : getMAX_TOTAL_MEMORY().hashCode());
//        result = prime * result + ((getCOUNT_SECONDARY() == null) ? 0 : getCOUNT_SECONDARY().hashCode());
//        result = prime * result + ((getFIRST_SEEN() == null) ? 0 : getFIRST_SEEN().hashCode());
//        result = prime * result + ((getLAST_SEEN() == null) ? 0 : getLAST_SEEN().hashCode());
//        result = prime * result + ((getQUANTILE_95() == null) ? 0 : getQUANTILE_95().hashCode());
//        result = prime * result + ((getQUANTILE_99() == null) ? 0 : getQUANTILE_99().hashCode());
//        result = prime * result + ((getQUANTILE_999() == null) ? 0 : getQUANTILE_999().hashCode());
//        result = prime * result + ((getQUERY_SAMPLE_TEXT() == null) ? 0 : getQUERY_SAMPLE_TEXT().hashCode());
//        result = prime * result + ((getQUERY_SAMPLE_SEEN() == null) ? 0 : getQUERY_SAMPLE_SEEN().hashCode());
//        result = prime * result + ((getQUERY_SAMPLE_TIMER_WAIT() == null) ? 0 : getQUERY_SAMPLE_TIMER_WAIT().hashCode());
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", schemaName=").append(schemaName);
//        sb.append(", digest=").append(digest);
//        sb.append(", digestText=").append(digestText);
//        sb.append(", countStar=").append(countStar);
//        sb.append(", sumTimerWait=").append(sumTimerWait);
//        sb.append(", minTimerWait=").append(minTimerWait);
//        sb.append(", avgTimerWait=").append(avgTimerWait);
//        sb.append(", maxTimerWait=").append(maxTimerWait);
//        sb.append(", sumLockTime=").append(sumLockTime);
//        sb.append(", sumErrors=").append(sumErrors);
//        sb.append(", sumWarnings=").append(sumWarnings);
//        sb.append(", sumRowsAffected=").append(sumRowsAffected);
//        sb.append(", sumRowsSent=").append(sumRowsSent);
//        sb.append(", sumRowsExamined=").append(sumRowsExamined);
//        sb.append(", sumCreatedTmpDiskTables=").append(sumCreatedTmpDiskTables);
//        sb.append(", sumCreatedTmpTables=").append(sumCreatedTmpTables);
//        sb.append(", sumSelectFullJoin=").append(sumSelectFullJoin);
//        sb.append(", sumSelectFullRangeJoin=").append(sumSelectFullRangeJoin);
//        sb.append(", sumSelectRange=").append(sumSelectRange);
//        sb.append(", sumSelectRangeCheck=").append(sumSelectRangeCheck);
//        sb.append(", SUM_SELECT_SCAN=").append(SUM_SELECT_SCAN);
//        sb.append(", SUM_SORT_MERGE_PASSES=").append(SUM_SORT_MERGE_PASSES);
//        sb.append(", sumSortRange=").append(sumSortRange);
//        sb.append(", SUM_SORT_ROWS=").append(SUM_SORT_ROWS);
//        sb.append(", SUM_SORT_SCAN=").append(SUM_SORT_SCAN);
//        sb.append(", SUM_NO_INDEX_USED=").append(SUM_NO_INDEX_USED);
//        sb.append(", SUM_NO_GOOD_INDEX_USED=").append(SUM_NO_GOOD_INDEX_USED);
//        sb.append(", SUM_CPU_TIME=").append(SUM_CPU_TIME);
//        sb.append(", MAX_CONTROLLED_MEMORY=").append(MAX_CONTROLLED_MEMORY);
//        sb.append(", MAX_TOTAL_MEMORY=").append(MAX_TOTAL_MEMORY);
//        sb.append(", COUNT_SECONDARY=").append(COUNT_SECONDARY);
//        sb.append(", FIRST_SEEN=").append(FIRST_SEEN);
//        sb.append(", LAST_SEEN=").append(LAST_SEEN);
//        sb.append(", QUANTILE_95=").append(QUANTILE_95);
//        sb.append(", QUANTILE_99=").append(QUANTILE_99);
//        sb.append(", QUANTILE_999=").append(QUANTILE_999);
//        sb.append(", QUERY_SAMPLE_TEXT=").append(QUERY_SAMPLE_TEXT);
//        sb.append(", QUERY_SAMPLE_SEEN=").append(QUERY_SAMPLE_SEEN);
//        sb.append(", QUERY_SAMPLE_TIMER_WAIT=").append(QUERY_SAMPLE_TIMER_WAIT);
//        sb.append(", serialVersionUID=").append(serialVersionUID);
//        sb.append("]");
//        return sb.toString();
//    }
}