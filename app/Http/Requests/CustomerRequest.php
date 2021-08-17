<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Validation\Rule;

class CustomerRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array
     */
    public function rules()
    {
        return [
            "name" => ["required", "min:3"],
            "gender" => ["required"],
            "address" => ["required"],
            "phone" => [
                "required",
                "regex:/^[0-9]{10}$/",
                Rule::unique("customers")
            ],
            "email" => [
                "required",
                "email",
                Rule::unique("customers")
            ],
        ];
    }
}
