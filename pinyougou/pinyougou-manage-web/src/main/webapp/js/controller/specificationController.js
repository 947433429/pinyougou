app.controller('specificationController',function ($scope,$controller,specificationService) {
    //继承
    $controller('baseController',{$scope:$scope});

    // 分页查询
    $scope.findPage = function (page, rows) {
        specificationService.findPage(page,rows).success(function (response) {
            // 更新记录列表
            $scope.list = response.rows;
            // 更新总记录数
            $scope.paginationConf.totalItems = response.total;
        });
    };


    //添加或修改
    $scope.save = function () {
        var obj;
        if($scope.entity.specification.id != null){
            obj = specificationService.update($scope.entity);
        }else {
            obj = specificationService.add($scope.entity);
        }
        obj.success(function (response) {
            if(response.success){
                //重新加载列表
                $scope.reloadList();
            }else{
                alert(response.message);
            }
        });
    }

    //查询主键查询
    $scope.findOne = function (id) {
        specificationService.findOne(id).success(function (response) {
            $scope.entity = response;
        });
    }



    //批量删除
    $scope.delete = function () {
        if($scope.selectedIds.length<1){
            alert("请选择要删除的记录");
            return ;
        }
        if(confirm("确认要删除选中的记录吗？")){
            specificationService.delete($scope.selectedIds).success(function (response) {
                if(response.success){
                    $scope.reloadList();
                    $scope.selectedIds = [];
                }else{
                    alert(response.message);
                }
            });
        }
    }

    //搜索
    $scope.searchEntity = {};
    $scope.search = function (page,rows) {
        specificationService.search(page,rows,$scope.searchEntity).success(function (response) {
            $scope.list = response.rows;
            $scope.paginationConf.totalItems = response.total;
        })
    }


    //添加行
    //$scope.entity = {specificationOptionList:[]};
    $scope.addTableRow = function () {
        $scope.entity.specificationOptionList.push({})
    }

    //删除行
    $scope.deleteTableRow = function (index) {
        $scope.entity.specificationOptionList.splice(index,1);
    }


});