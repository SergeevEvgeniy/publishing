﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace CloudPublishing.Business.Resources.Messages {
    using System;
    
    
    /// <summary>
    ///   A strongly-typed resource class, for looking up localized strings, etc.
    /// </summary>
    // This class was auto-generated by the StronglyTypedResourceBuilder
    // class via a tool like ResGen or Visual Studio.
    // To add or remove a member, edit your .ResX file then rerun ResGen
    // with the /str option, or rebuild your VS project.
    [global::System.CodeDom.Compiler.GeneratedCodeAttribute("System.Resources.Tools.StronglyTypedResourceBuilder", "15.0.0.0")]
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
    [global::System.Runtime.CompilerServices.CompilerGeneratedAttribute()]
    public class Error {
        
        private static global::System.Resources.ResourceManager resourceMan;
        
        private static global::System.Globalization.CultureInfo resourceCulture;
        
        [global::System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1811:AvoidUncalledPrivateCode")]
        internal Error() {
        }
        
        /// <summary>
        ///   Returns the cached ResourceManager instance used by this class.
        /// </summary>
        [global::System.ComponentModel.EditorBrowsableAttribute(global::System.ComponentModel.EditorBrowsableState.Advanced)]
        public static global::System.Resources.ResourceManager ResourceManager {
            get {
                if (object.ReferenceEquals(resourceMan, null)) {
                    global::System.Resources.ResourceManager temp = new global::System.Resources.ResourceManager("CloudPublishing.Business.Resources.Messages.Error", typeof(Error).Assembly);
                    resourceMan = temp;
                }
                return resourceMan;
            }
        }
        
        /// <summary>
        ///   Overrides the current thread's CurrentUICulture property for all
        ///   resource lookups using this strongly typed resource class.
        /// </summary>
        [global::System.ComponentModel.EditorBrowsableAttribute(global::System.ComponentModel.EditorBrowsableState.Advanced)]
        public static global::System.Globalization.CultureInfo Culture {
            get {
                return resourceCulture;
            }
            set {
                resourceCulture = value;
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Неверный идентификатор сотрудника.
        /// </summary>
        public static string InvalidIdentifierEmployee {
            get {
                return ResourceManager.GetString("InvalidIdentifierEmployee", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Ошибка при получении данных сотрудника.
        /// </summary>
        public static string NoDataAqueiredEmployee {
            get {
                return ResourceManager.GetString("NoDataAqueiredEmployee", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Ошибка получения данных с удаленного хоста.
        /// </summary>
        public static string NoDataAquiredRemoteHost {
            get {
                return ResourceManager.GetString("NoDataAquiredRemoteHost", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Журналист не найден.
        /// </summary>
        public static string NotFoundJournalist {
            get {
                return ResourceManager.GetString("NotFoundJournalist", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Пользователь не найден.
        /// </summary>
        public static string NotFoundUser {
            get {
                return ResourceManager.GetString("NotFoundUser", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Сначала необходимо указать другого главного редактора.
        /// </summary>
        public static string RoleChangeFaildChiefEditor {
            get {
                return ResourceManager.GetString("RoleChangeFaildChiefEditor", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Невозможно получить данные стороннего сервиса.
        /// </summary>
        public static string UnavaibleRemoteHost {
            get {
                return ResourceManager.GetString("UnavaibleRemoteHost", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Ошибка обновления данных пользователя: {0}.
        /// </summary>
        public static string UpdateFailedEmployee {
            get {
                return ResourceManager.GetString("UpdateFailedEmployee", resourceCulture);
            }
        }
    }
}