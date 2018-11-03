app.service('brandService',function ($http) {

    //查询单个信息
    this.findOne = function (id) {
        return $http.get('../brand/findOne.do?id='+id);
    }

    // 分页查询
    this.findPage = function (page, rows) {
        return $http.get("../brand/findPage.do?page=" + page + "&rows=" +rows);
    }

    //搜索
    this.search = function (page,rows,searchEntity) {
        return $http.post('../brand/search.do?page='+page+'&rows='+rows,searchEntity);
    }

    //添加
    this.add = function (entity) {
        return $http.post('../brand/add.do',entity)
    }

    //修改
    this.update = function (entity) {
        return $http.post('../brand/update.do',entity)
    }

    //批量删除
    this.delete = function (selectedIds) {
        return $http.get('../brand/delete.do?ids='+selectedIds);
    }
});