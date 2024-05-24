<template>
  <div class="main-page">
    <el-container class="container">
      <!-- Header -->
      <el-header class="header" :style="{ backgroundColor: headerColor }">
        <h1>作者管理</h1>
      </el-header>

      <el-container class="content-container">
        <!-- Aside -->
        <el-aside class="aside">
          <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>功能菜单</span>
              </template>
              <el-menu-item-group>
                <el-menu-item @click="pushHome" index="1-1">我的主页</el-menu-item>
                <el-menu-item @click="docManage" index="1-2">文章管理</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>主题颜色</span>
              </template>
              <el-menu-item-group>
                <el-menu-item @click="yellowPanel" index="2-1">黄色风格</el-menu-item>
                <el-menu-item @click="bluePanel" index="2-2">蓝色风格</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-menu-item index="3" disabled>
              <i class="el-icon-document"></i>
              <span slot="title">导航三</span>
            </el-menu-item>
            <el-menu-item index="4">
              <i class="el-icon-setting"></i>
              <span slot="title">导航四</span>
            </el-menu-item>
          </el-menu>
        </el-aside>

        <!-- Main -->
        <el-main class="main" :style="{ backgroundColor: mainColor }">
          <div class="upfield">
            <el-button type="primary" icon="el-icon-plus" @click="adduser">新增</el-button>
            <el-input v-model="inputUserName" placeholder="请输入姓名" suffix-icon="el-icon-search" class="right-input"></el-input>
            <el-button type="primary" icon="el-icon-search" @click="searchuser">查询</el-button>
          </div>
          <el-table
            :data="authors"
            :border="true"
            style="width: 100%">
            <el-table-column
              prop="date"
              label="日期"
              width="150">
            </el-table-column>
            <el-table-column
              prop="name"
              label="姓名"
              width="120">
            </el-table-column>
            <el-table-column
              prop="province"
              label="省份"
              width="120">
            </el-table-column>
            <el-table-column
              prop="city"
              label="市区"
              width="120">
            </el-table-column>
            <el-table-column
              prop="address"
              label="地址"
              width="300">
            </el-table-column>
            <el-table-column
              prop="zip"
              label="邮编"
              width="120">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="100">
              <template slot-scope="scope">
                <el-button @click="handleEditClick(scope.row)" type="text" size="small">编辑</el-button>
                <el-button @click="handleDeleteClick(scope.row)" type="text" size="small">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[10,15,20]"
            :page-size="10"
            layout="total, sizes, prev, pager, next, jumper"
            :total=totalItems>
          </el-pagination>
        </el-main>
      </el-container>
    </el-container>

    <!-- 属性绑定语法是在属性名前加一个冒号（:），用于将Vue组件中的数据绑定到HTML元素的属性上。这样，当数据发生变化时，HTML元素上的属性也会被更新。 -->
    <!-- Form Dialog -->
    <el-dialog :visible.sync="isadduser" :title='this.text + "作者"'  :modal="false" class="form">
      <el-form :model="form" label-position="left" label-width="80px">
        <el-form-item label="姓名">
          <el-input v-model="form.name" placeholder="姓名" class="full-width"></el-input>
        </el-form-item>
        <!-- 二级联动 -->
        <el-form-item label="日期">
          <el-date-picker
            v-model="form.date"
            type="date"
            placeholder="选择日期"
            class="full-width">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="省份">
          <el-select v-model="form.province" class="full-width" placeholder="请选择" @change="loadCities(form.province)">
          <el-option
            v-for="item in provinces"
            :key="item.name"
            :label="item.label"
            :value="item.name">
          </el-option>
        </el-select>
        </el-form-item>
        <el-form-item label="市区">
          <el-select v-model="form.city" class="full-width" placeholder="请选择">
          <el-option
            v-for="item in cities"
            :key="item.name"
            :label="item.label"
            :value="item.name">
          </el-option>
        </el-select>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" placeholder="地址" class="full-width"></el-input>
        </el-form-item>
        <el-form-item label="邮编">
          <el-input v-model="form.zip" placeholder="邮编" class="full-width"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="quitForm">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'MainPage',
  data() {
    return {
      currentPage: 1,
      totalItems: 0,
      pageSize: 10,
      form: {
        name: '',
        date: '',
        province: '',
        city: '',
        address: '',
        zip: ''
      },
      inputUserName: '',
      authors: [],
      headerColor: '#1E88E5',
      mainColor: '#BBDEFB',
      isadduser: false,
      provinces: [],
      cities: [],
      text:'新增'
    };
  },
  mounted() {
    this.loadProvinces();
    this.fetchData();
  },
  watch: {
    // 监听省份变化
    'form.province'(newValue, oldValue) {
      if (this.text === '编辑') {
        // 获取城市数据
        this.loadCities(newValue);
        if(oldValue !== ''){
          this.form.city = '';
          this.form.address = '';
        }
      } else {
        // 如果省份为空，将城市数据清空
        this.form.city = '';
        this.cities = [];
        this.form.address = '';
      }
    }
  },
  methods: {
    
    fetchData() {
      const url = 'http://localhost:8081/api/authors';
      axios.get(url, {
        params: {
          page: this.currentPage,
          pageSize: this.pageSize
        }
      }).then(response => {
        const pageInfo = response.data;
        // 遍历作者列表，修改日期格式
        pageInfo.list.forEach(author => {
          author.date = this.formatDate(author.date);
        });
        // 将修改后的作者列表赋值给 this.authors
        this.authors = pageInfo.list;
        this.totalItems = pageInfo.total;
      }).catch(error => {
        console.error("Error fetching data: ", error);
      });
    },
    formatDate(dateString) {
    let date = new Date(dateString);
    let year = date.getFullYear();
    let month = (date.getMonth() + 1).toString().padStart(2, '0');
    let day = date.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
    },
    handleCurrentChange(page) {
      this.currentPage = page;
      this.fetchData();
    },
    handleSizeChange(size) {
      this.pageSize = size;
      this.fetchData();
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    handleEditClick(row) {
      this.text = '编辑';
      console.log('Edit:', row);
      this.isadduser = true;
      this.form.address = row.address;
      this.form.province = row.province;
      this.loadCities(row.province);
      this.form.city = row.city;
      console.log(this.form.city);
      this.form.date = row.date;
      this.form.name = row.name;
      this.form.zip = row.zip;

      // 编辑页面

    },
    handleDeleteClick(row) {
      // 删除操作
      this.$confirm('此操作将永久删除该作者, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios.delete('http://localhost:8081/api/deleteauthor', {
          params: {
            id: row.id
          }
        }).then(response => {
          this.$message.success('删除成功');
          if(this.authors.length === 1 && this.currentPage > 1){
            this.currentPage = this.currentPage - 1;
          }
          this.handleCurrentChange(this.currentPage);
      }).catch(error => {
        this.$message.error('删除失败');
        console.error(error);
      });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
      
    },
    pushHome(){
      this.$router.push('/home');
    },
    docManage(){
      this.$router.push('/docmanage');
    },
    yellowPanel(){
      this.headerColor = '#b2b528';
      this.mainColor = '#fff957';
    },
    bluePanel(){
      this.headerColor = '#1E88E5';
      this.mainColor = '#BBDEFB';
    },
    adduser(){
      this.quitForm();
      this.text = '新增';
      console.log('adduser');
      this.isadduser = true;
    },
    searchuser(){
      console.log('searchuser');
      const url = 'http://localhost:8081/api/searchauthor';
      axios.get(url, {
        params: {
          name: this.inputUserName
        }
      }).then(response => {
        const pageInfo = response.data;
        // 遍历作者列表，修改日期格式
        pageInfo.list.forEach(author => {
          author.date = this.formatDate(author.date);
        });
        // 将修改后的作者列表赋值给 this.authors
        this.authors = pageInfo.list;
        this.totalItems = pageInfo.total;
      }).catch(error => {
        console.error("Error fetching data: ", error);
      });
    },
    quitForm(){
      this.form = {
        name: '',
        date: '',
        province: '',
        city: '',
        address: '',
        zip: ''
      };
      this.isadduser = false;
    },
    submitForm(){
      this.isadduser = false;
      axios.post('http://localhost:8081/api/addauthor', this.form)
        .then((response) => {
          // 请求成功处理
          this.$message.success(this.text + '成功');
          this.fetchData();
        })
        .catch((error) => {
          // 请求失败处理
          this.$message.error(this.text + '失败');
          console.error(error);
        })
        .finally(() => {
          this.form = {
            name: '',
            date: '',
            province: '',
            city: '',
            address: '',
            zip: ''
          };
        })
      
    },
    loadProvinces() {
      axios.get('https://restapi.amap.com/v3/config/district', {
        params: {
          key: 'd4ee113a63fd12a5c96e8a56d5b56469',
          keywords: '中国',
          subdistrict: 1
        }
      })
      .then(response => {
        // 请求成功，处理返回的数据
        if (response.data && response.data.districts) {
          const provinces = response.data.districts[0].districts.map(district => ({
            name: district.name,
            level: district.level
          }));
          this.provinces = provinces;
        }
      })
      .catch(error => {
        // 请求失败，处理错误信息
        console.error('Error:', error);
      });
    },
    loadCities(province) {
      axios.get('https://restapi.amap.com/v3/config/district', {
        params: {
          key: 'd4ee113a63fd12a5c96e8a56d5b56469', 
          keywords: province, // 根据选择的省份名称查询对应的市区数据
          subdistrict: 2
        }
      })
      .then(response => {
        // 请求成功，处理返回的数据
        if (response.data && response.data.districts && response.data.districts.length > 0) {
          const cities = response.data.districts[0].districts.map(district => ({
            name: district.name,
            level: district.level
          }));
          this.cities = cities;
        }
      })
      .catch(error => {
        // 请求失败，处理错误信息
        console.error('Error:', error);
      });
    }
  },

};
</script>

<style scoped>
.main-page {
  position: fixed;
  height: 100%;
  width: 100%;
  top: 0%;
  left: 0%;
  height: 90vh;
}
.upfield {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}
.right-input {
  width: 250px;
  margin-left: auto;
  margin-right: 10px;
}
.form {
  height: 90%;
}
.container {
  height: 100%;
}

.content-container {
  height: 100%;
}

.header {
  background-color: #1E88E5; /* 深蓝色 */
  color: #fff;
}

.aside {
  background-color: #fff; /* 白色 */
  height: 100%;
}

.main {
  background-color: #BBDEFB; /* 浅蓝色 */
  height: 100%;
}
.full-width {
  width: 100%;
}
.el-date-editor.el-input, .el-date-editor.el-input__inner {
  width: 100%;
}

/* 媒体查询 */
@media screen and (max-width: 800px) {
  .aside {
    display: none; /* 当屏幕宽度小于800px时，隐藏aside */
  }
}
</style>
