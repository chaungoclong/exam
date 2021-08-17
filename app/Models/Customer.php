<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Customer extends Model
{
    use HasFactory;

    protected $fillable = ["name", "email", "phone", "gender", "address"];

    public function getGenderAttribute($value) {
        return $value == 1 ? "male" : "female";
    }
}
