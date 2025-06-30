import Foundation
import Capacitor
import JPush

/**
 * JPush Capacitor Plugin for iOS
 * Provides push notification functionality using JPush SDK
 */
@objc(JPushPluginPlugin)
public class JPushPluginPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "JPushPluginPlugin"
    public let jsName = "JPushPlugin"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "init", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "getRegistrationID", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "setAlias", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "deleteAlias", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "addTags", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "deleteTags", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "cleanTags", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "setBadge", returnType: CAPPluginReturnPromise)
    ]
    private let implementation = JPushPlugin()

    public override func load() {
        super.load()
        implementation.setPlugin(self)

        // Setup JPush notifications
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(handleJPushRegistration(_:)),
            name: NSNotification.Name("JPushRegistrationNotification"),
            object: nil
        )
    }

    @objc func handleJPushRegistration(_ notification: Notification) {
        if let registrationId = notification.userInfo?["registrationId"] as? String {
            notifyListeners("jpushRegistrationId", data: ["registrationId": registrationId])
        }
    }

    @objc func `init`(_ call: CAPPluginCall) {
        implementation.initJPush { [weak self] success, message in
            DispatchQueue.main.async {
                if success {
                    call.resolve(["success": true, "message": message])
                } else {
                    call.reject(message)
                }
            }
        }
    }

    @objc func getRegistrationID(_ call: CAPPluginCall) {
        let registrationId = implementation.getRegistrationID()
        call.resolve(["registrationId": registrationId])
    }

    @objc func setAlias(_ call: CAPPluginCall) {
        guard let alias = call.getString("alias") else {
            call.reject("Alias is required")
            return
        }

        implementation.setAlias(alias) { [weak self] success, message in
            DispatchQueue.main.async {
                if success {
                    call.resolve(["success": true, "message": message])
                } else {
                    call.reject(message)
                }
            }
        }
    }

    @objc func deleteAlias(_ call: CAPPluginCall) {
        implementation.deleteAlias { [weak self] success, message in
            DispatchQueue.main.async {
                if success {
                    call.resolve(["success": true, "message": message])
                } else {
                    call.reject(message)
                }
            }
        }
    }

    @objc func addTags(_ call: CAPPluginCall) {
        guard let tagsArray = call.getArray("tags") as? [String] else {
            call.reject("Tags array is required")
            return
        }

        implementation.addTags(Set(tagsArray)) { [weak self] success, message in
            DispatchQueue.main.async {
                if success {
                    call.resolve(["success": true, "message": message])
                } else {
                    call.reject(message)
                }
            }
        }
    }

    @objc func deleteTags(_ call: CAPPluginCall) {
        guard let tagsArray = call.getArray("tags") as? [String] else {
            call.reject("Tags array is required")
            return
        }

        implementation.deleteTags(Set(tagsArray)) { [weak self] success, message in
            DispatchQueue.main.async {
                if success {
                    call.resolve(["success": true, "message": message])
                } else {
                    call.reject(message)
                }
            }
        }
    }

    @objc func cleanTags(_ call: CAPPluginCall) {
        implementation.cleanTags { [weak self] success, message in
            DispatchQueue.main.async {
                if success {
                    call.resolve(["success": true, "message": message])
                } else {
                    call.reject(message)
                }
            }
        }
    }

    @objc func setBadge(_ call: CAPPluginCall) {
        let badge = call.getInt("badge") ?? 0
        implementation.setBadge(badge) { [weak self] success, message in
            DispatchQueue.main.async {
                if success {
                    call.resolve(["success": true, "message": message])
                } else {
                    call.reject(message)
                }
            }
        }
    }
}
