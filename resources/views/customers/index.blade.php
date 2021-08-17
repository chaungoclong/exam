@extends('layouts.app')

@section('content')
	<div class="container-fluid">
		<div>
			@if (session('error'))
				<div class="alert alert-danger">
					{{ session('error') }}
				</div>
			@endif

			@if (session('success'))
				<div class="alert alert-success">
					{{ session('success') }}
				</div>
			@endif
		</div>

		<div class="" style="display: flex; justify-content: flex-end;">
			<form class="form-inline" action="{{ route('customer.index') }}">
			  <div class="form-group">
			    <input type="search" class="form-control" id="search" name="search" value="{{ $search ?? '' }}">
			  </div>
			  <button type="submit" id="btnSearch" class="btn btn-success">Search</button>
			</form>
		</div>

		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>id</th>
					<th>name</th>
					<th>gender</th>
					<th>address</th>
					<th>phone</th>
					<th>email</th>
				</tr>
			</thead>
			<tbody>
				@foreach ($customers as $customer)
					<tr>
						<td>{{ $customer->id }}</td>
						<td>{{ $customer->name }}</td>
						<td>{{ $customer->gender }}</td>
						<td>{{ $customer->address }}</td>
						<td>{{ $customer->phone }}</td>
						<td>{{ $customer->email }}</td>
					</tr>
				@endforeach
				<tr>
					<td colspan="5">
						@if (isset($search) && $search !== "")
							{{ 	$customers->appends(["search" => $search])->links() }}
						@else
							{{ 	$customers->links() }}
						@endif
					</td>
				</tr>
			</tbody>
		</table>
	</div>
@stop

@push('script')
<script type="text/javascript">
	$(function() {
		
	});
</script>
@endpush