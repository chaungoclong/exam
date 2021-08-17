<div class="col-sm-3 sidenav">
  <h4>EXAM</h4>
  <ul class="nav nav-pills nav-stacked">
    <li class="{{ request()->is('customer') ? 'active' : '' }}"><a href="{{ route('customer.index') }}">List</a></li>
    <li class="{{ request()->is('customer/create') ? 'active' : '' }}"><a href="{{ route('customer.create') }}">Add</a></li>
  </ul><br>
  <div class="input-group">
    <input type="text" class="form-control" placeholder="Search Blog..">
    <span class="input-group-btn">
      <button class="btn btn-default" type="button">
        <span class="glyphicon glyphicon-search"></span>
      </button>
    </span>
  </div>
</div>