var API = 'rest';
var VERSION = 'v1.0.0';
var ROOT_ID = '138875005';

angular
  .module('sctbrowser', [
    'ngResource',
    'ngUrlBind',
    'ui.bootstrap',
    'ui.layout'
  ])
  .config([
    '$provide',
    function ($provide) {
      $provide.decorator("$exceptionHandler", [
        "$delegate", "$injector",
        function ($delegate, $injector) {
          return function (exception, cause) {

            var $root = $injector.get("$rootScope");
            $root.error = {
              exception: exception,
              cause: cause
            };

            $delegate(exception, cause);

            $('#error-modal').modal({
              show: true,
              backdrop: 'static',
              keyboard: true
            });
          };
        }]);
    }])
  .factory('Search', [
    '$resource',
    function ($resource) {
      return $resource(API + '/' + VERSION + '/search', {
        terms: 'terms',
        offset: 'offset',
        limit: 'limit',
        count: 'count'
      }, {
        query: {method: 'GET'}
      });
    }])
  .factory('Refsets', [
    '$resource',
    function ($resource) {
      return $resource(API + '/' + VERSION + '/refsets', {
      }, {
        query: {method: 'GET', isArray: true}
      });
    }])
  .factory('Subsets', [
    '$resource',
    function ($resource) {
      return $resource(API + '/' + VERSION + '/subsets', {
      }, {
        query: {method: 'GET', isArray: true}
      });
    }])
  .factory('Members', [
    '$resource',
    function ($resource) {
      return $resource(API + '/' + VERSION + '/members', {
        conceptId: 'conceptId',
        offset: 'offset',
        limit: 'limit',
        count: 'count'
      }, {
        query: {method: 'GET'}
      });
    }])
  .factory('Mappings', [
    '$resource',
    function ($resource) {
      return $resource(API + '/' + VERSION + '/mappings', {
        conceptId: 'conceptId'
      }, {
        query: {method: 'GET', isArray: true}
      });
    }])
  .factory('MemberOf', [
    '$resource',
    function ($resource) {
      return $resource(API + '/' + VERSION + '/memberof', {
        conceptId: 'conceptId',
        offset: 'offset',
        limit: 'limit',
        count: 'count'
      }, {
        query: {method: 'GET'}
      });
    }])
  .factory('Details', [
    '$resource',
    function ($resource) {
      return $resource(API + '/' + VERSION + '/details', {
        conceptId: 'conceptId'
      }, {
        query: {method: 'GET'}
      });
    }])
  .controller('Controller', [
    '$scope', '$log', 'ngUrlBind', 'Search', 'Refsets', 'Subsets', 'Mappings', 'Members', 'MemberOf', 'Details',
    function ($scope, $log, ngUrlBind, Search, Refsets, Subsets, Mappings, Members, MemberOf, Details) {

      $scope.spinners = {
        details: true,
        mappings: true,
        memberOf: true,
        members: true,
        search: false
      };

      $scope.data = {
        conceptId: ROOT_ID,
        search: {
          terms: '',
          currentPage: 1,
          pageSize: 10,
          concepts: [],
          count: 0,
          maxSize: 5
        },
        refsets: {
          concepts: []
        },
        subsets: {
          concepts: []
        },
        details: [],
        mappings: {
          concepts: []
        },
        members: {
          currentPage: 1,
          pageSize: 10,
          concepts: [],
          count: 0,
          maxSize: 5
        },
        memberOf: {
          currentPage: 1,
          pageSize: 10,
          concepts: [],
          count: 0,
          maxSize: 5
        }
      };

      $scope.selectInTree = function () {

        var paths = $scope.data.details.paths;
        var from = ROOT_ID;
        var to = $scope.data.conceptId;

        selectInTree(paths, from, to);
      };

      $scope.search = function () {

        $scope.data.search.concepts = [];
        $scope.data.search.currentPage = 1;
        $scope.searchPage();
      };

      $scope.searchPage = function () {

        if ($scope.data.search.terms.trim().length < 3) {
          $scope.data.search.concepts = [];
          $scope.data.search.count = 0;
          return;
        }

        $scope.data.search.concepts = [];
        $scope.spinners.search = true;

        Search.query(
          {
            terms: $scope.data.search.terms,
            offset: ($scope.data.search.currentPage - 1) * $scope.data.search.pageSize,
            limit: $scope.data.search.pageSize,
            count: $scope.data.search.currentPage === 1
          })
          .$promise
          .then(function (result) {
            $scope.spinners.search = false;
            $scope.data.search.concepts = result.concepts;
            if (result.count) {
              $scope.data.search.count = result.count;
            }
          });
      };

      $scope.mappings = function () {

        $scope.data.mappings.concepts = [];
        $scope.spinners.mappings = true;
      
        Mappings.query(
          {
            conceptId: $scope.data.conceptId
          })
          .$promise
          .then(function (result) {
            $scope.spinners.mappings = false;
            $scope.data.mappings.concepts = result;
          });
      };
      
      $scope.membersPage = function () {

        $scope.data.members.concepts = [];
        $scope.spinners.members = true;

        Members.query(
          {
            conceptId: $scope.data.conceptId,
            offset: ($scope.data.members.currentPage - 1) * $scope.data.members.pageSize,
            limit: $scope.data.members.pageSize,
            count: $scope.data.members.currentPage === 1
          })
          .$promise
          .then(function (result) {
            $scope.spinners.members = false;
            $scope.data.members.concepts = result.concepts;
            if (result.count) {
              $scope.data.members.count = result.count;
            }
          });
      };

      $scope.details = function () {

        $scope.data.details = [];
        $scope.spinners.details = true;

        Details.query(
          {
            conceptId: $scope.data.conceptId
          })
          .$promise
          .then(function (result) {
            $scope.spinners.details = false;
            $scope.data.details = result;
          });
      };

      $scope.memberOfPage = function () {

        $scope.data.memberOf.concepts = [];
        $scope.spinners.memberOf = true;

        MemberOf.query(
          {
            conceptId: $scope.data.conceptId,
            offset: ($scope.data.memberOf.currentPage - 1) * $scope.data.memberOf.pageSize,
            limit: $scope.data.memberOf.pageSize,
            count: $scope.data.memberOf.currentPage === 1
          })
          .$promise
          .then(function (result) {
            $scope.spinners.memberOf = false;
            $scope.data.memberOf.concepts = result.concepts;
            if (result.count) {
              $scope.data.memberOf.count = result.count;
            }
          });
      };

      $scope.browseSelect = function (id) {
        select(id);
      };

      $scope.searchSelect = function (concept) {
        select(concept.id);
      };

      $scope.refsetSelect = function (refset) {
        select(refset.id);
      };

      $scope.subsetSelect = function (subset) {
        select(subset.refset_id);
      };

      $scope.relationshipSelect = function (relationship) {
        select(relationship.concept_id);
      };

      $scope.mappingSelect = function (mapping) {
        select(mapping.refset_id);
      };

      $scope.memberSelect = function (member) {
        select(member.id);
      };

      $scope.memberOfSelect = function (memberOf) {
        select(memberOf.id);
      };

      $scope.propertySelect = function (property) {
        select(property.module_id);
      };

      function selectInTree(paths, from, to) {

        var tree = $("#tree").fancytree("getTree");
        var node = tree.getNodeByKey(from);
        node.li.scrollIntoView();

        if (from === to) {
          node.setActive(true);
          return;
        }

        node.setExpanded(true).done(function () {
          $.grep(paths, function (parent) {
            return parent.parent_id === from;
          }).forEach(function (parent) {
            selectInTree(paths, parent.id, to);
          });
        });
      }

      function select(id) {

        if ($scope.data.conceptId === id) {
          return;
        }

        $scope.data.conceptId = id;
        $scope.details();

        $scope.data.memberOf.concepts = [];
        $scope.data.memberOf.currentPage = 1;
        $scope.memberOfPage();

        $scope.data.members.concepts = [];
        $scope.data.members.currentPage = 1;
        $scope.membersPage();

        $scope.mappings();
      }

      function selectTab(tab) {

        if ($scope.rightTab === tab) {
          return;
        }

        $scope.rightTab = tab;
      }

      function urlBind() {

        if (!$scope.id) {
          $scope.id = '';
        }

        if (!$scope.tab) {
          $scope.tab = 0;
        }

        $scope.$watch('id', function (id) {
          select(id);
        });

        $scope.$watch('data.conceptId', function (conceptId) {
          $scope.id = conceptId;
        });

        $scope.$watch('tab', function (tab) {
          selectTab(tab);
        });

        $scope.$watch('rightTab', function (rightTab) {
          $scope.tab = rightTab;
        });

        ngUrlBind($scope, 'id');
        ngUrlBind($scope, 'tab');
      }

      function refsets() {
        $scope.data.refsets.concepts = Refsets.query({});
      }

      function subsets() {
        $scope.data.subsets.concepts = Subsets.query({});
      }

      urlBind();
      refsets();
      subsets();
    }])
  .directive('fancytree', [
    '$log',
    function ($log) {

      var glyphOptions = {
        map: {
          doc: "glyphicon glyphicon-file node-icon",
          docOpen: "glyphicon glyphicon-file node-icon",
          error: "glyphicon glyphicon-warning-sign node-control",
          expanderClosed: "glyphicon glyphicon-plus node-control",
          expanderLazy: "glyphicon glyphicon-plus node-control",
          expanderOpen: "glyphicon glyphicon-minus node-control",
          folder: "glyphicon glyphicon-duplicate node-icon",
          folderOpen: "glyphicon glyphicon-duplicate node-icon",
          loading: "glyphicon glyphicon-refresh glyphicon-spin node-control"
        }
      };

      return {
        restrict: 'EAC',
        scope: {
          id: "@",
          select: '&select'
        },
        link: function (scope, element, attrs) {

          $('#' + scope.id).fancytree({
            extensions: ["glyph"],
            glyph: glyphOptions,
            source: $.ajax({
              url: API + '/' + VERSION + '/browse',
              dataType: "json",
              data: {
                concept: true,
                conceptId: ROOT_ID
              },
              complete: function () {
                var root = $('#' + scope.id)
                  .fancytree("getRootNode")
                  .getFirstChild();
                if (null !== root) {
                  root.setExpanded(true);
                }
              }
            }),
            lazyLoad: function (event, data) {
              var node = data.node;
              data.result = {
                url: API + '/' + VERSION + '/browse',
                dataType: "json",
                data: {
                  conceptId: node.key
                },
                cache: false
              };
            },
            activate: function (event, data) {
              scope.select({id: data.node.key});
            }
          });
        }
      };
    }]);