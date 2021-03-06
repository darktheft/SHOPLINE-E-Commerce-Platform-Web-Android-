"""webApp URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path,include
from . import views
from django.views.generic import TemplateView

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', views.Login,name='login'),
    path('postsign/', views.postsign),
    path('search/', views.postsign),
    path('api/v1/',include('social_django.urls', namespace='social')),
    #path('logout/',views.logout,name="log"),
    path('SignUp/',views.SignUp,name="SignUp"),
    path('welcome/',views.WithoutRegistration,name="welcome"),
    path('postSignUp/',views.postSignUp,name="postSignUp"),
    path('productView/',views.productView,name="productView"),
    path('checkout/',views.checkout,name="Checkout"),
    path('cart/',views.cart,name="cart")
]