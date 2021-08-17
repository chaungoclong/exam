@extends('layouts.app')

@section('content')
<div class="container-fluid">
	<form action="{{ route("customer.store") }}" method="post" enctype="multipart/form-data">
		@csrf

		<legend>Form title</legend>

		<div class="form-group">
			<label for="name">name</label>
			<input type="text" class="form-control" name="name" id="name" value="{{ old("name") }}">
			@error('name')
				<span class="text-danger">{{ $message }}</span>
			@enderror
		</div>

		<div class="form-group">
			<label for="email">email</label>
			<input type="text" class="form-control" name="email" id="email" value="{{ old("email") }}">
			@error('email')
				<span class="text-danger">{{ $message }}</span>
			@enderror
		</div>

		<div class="form-group">
			<label for="phone">phone</label>
			<input type="text" class="form-control" name="phone" id="phone" value="{{ old("phone") }}">
			@error('phone')
				<span class="text-danger">{{ $message }}</span>
			@enderror
		</div>

		<div class="form-group">
			<label for="phone">address</label>
			<input type="text" class="form-control" name="address" id="address" value="{{ old("address") }}">
			@error('address')
				<span class="text-danger">{{ $message }}</span>
			@enderror
		</div>

		<div class="form-group">
			<label class="radio-inline">
				<input type="radio" name="gender" value="1" {{ old('gender') == '1' ? 'checked' : '' }}>male
			</label>
			<label class="radio-inline">
				<input type="radio" name="gender" value="0" {{ old('gender') == '0' ? 'checked' : '' }}>female
			</label>
			@error('gender')
				<br>
				<span class="text-danger">{{ $message }}</span>
			@enderror
		</div>

		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</div>
@stop