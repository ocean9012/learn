<template>
  <div class="option_first">
    <div class="sub">
      <span class="title">我的订阅</span>
      <div class="table">
        <el-table :data="tableData" style="width: 100%" @row-click="rowClick">
          <el-table-column prop="name" label="课程名称" width="300" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="courseTypeName" label="类型" width="200"></el-table-column>
          <el-table-column width="100" align="right">
            <template slot-scope="scope">
              <el-popconfirm
                confirmButtonText="确认"
                cancelButtonText="取消"
                icon="el-icon-info"
                iconColor="red"
                title="是否取消订阅？"
                @onConfirm="handleCancel(scope.$index, scope.row)"
              >
                <el-button size="mini" type="danger" @click.stop slot="reference">Cancel</el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="pagination">
        <el-pagination
          background
          @current-change="currentChange"
          :page-size="page.size"
          :current-page="page.current"
          layout="prev, pager, next"
          :total="total"
          style="text-align: center"
          :hide-on-single-page="true"
        ></el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import { getSubscribe, removeSubscribe } from '@/api/course'
export default {
  name: 'OptionFirst',
  data() {
    return {
      tableData: '',
      total: 0,
      page: {
        size: 5,
        current: 1,
        sort: '-id'
      }
    }
  },
  mounted() {
    this.info()
  },
  methods: {
    info() {
      getSubscribe(this.page).then(res => {
        this.tableData = res.data.content
        this.total = res.data.totalElements
      })
    },
    currentChange(val) {
      this.page.current = val
      getSubscribe(this.page).then(res => {
        this.tableData = res.data.content
        this.total = res.data.totalElements
      })
    },
    handleCancel(index, row) {
      removeSubscribe(row.id).then(res => {
        this.$message({
          showClose: true,
          duration: 2500,
          message: res.data,
          type: 'success'
        })
        if (this.tableData.length === 1 && this.page.current !== 1) {
          this.page.current--
        }
        getSubscribe(this.page).then(res => {
          this.tableData = res.data.content
          this.total = res.data.totalElements
        })
      })
    },
    rowClick(row, column, event) {
      this.$router.push({ path: '/video', query: { id: row.id } })
    }
  }
}
</script>

<style scoped>
.sub {
  width: 600px;
  height: auto;
  position: absolute;
  left: 700px;
  top: 120px;
}
.title {
  color: #303030;
  font-size: 25px;
}
.table {
  margin-top: 40px;
}
.pagination {
  margin-top: 30px;
}
</style>
