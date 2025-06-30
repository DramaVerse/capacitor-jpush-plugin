import Foundation
import UIKit
import UserNotifications
import JPush
import Capacitor

@objc public class JPushPlugin: NSObject {
    private weak var plugin: CAPPlugin?
    private var isInitialized = false

    public func setPlugin(_ plugin: CAPPlugin) {
        self.plugin = plugin
    }

    @objc public func initJPush(completion: @escaping (Bool, String) -> Void) {
        guard !isInitialized else {
            completion(true, "JPush already initialized")
            return
        }

        // Request notification permissions
        UNUserNotificationCenter.current().requestAuthorization(options: [.alert, .badge, .sound]) { granted, error in
            if let error = error {
                completion(false, "Failed to request notification permissions: \(error.localizedDescription)")
                return
            }

            DispatchQueue.main.async {
                UIApplication.shared.registerForRemoteNotifications()
            }

            // Initialize JPush
            let appKey = Bundle.main.object(forInfoDictionaryKey: "JPushAppKey") as? String ?? "your_jpush_appkey_here"
            let channel = Bundle.main.object(forInfoDictionaryKey: "JPushChannel") as? String ?? "App Store"
            let isProduction = Bundle.main.object(forInfoDictionaryKey: "JPushProduction") as? Bool ?? true

            JPUSHService.setup(withOption: nil, appKey: appKey, channel: channel, apsForProduction: isProduction)
            JPUSHService.registrationIDCompletionHandler { registrationId in
                if let regId = registrationId {
                    NotificationCenter.default.post(
                        name: NSNotification.Name("JPushRegistrationNotification"),
                        object: nil,
                        userInfo: ["registrationId": regId]
                    )
                }
            }

            self.isInitialized = true
            completion(true, "JPush initialized successfully")
        }
    }

    @objc public func getRegistrationID() -> String {
        return JPUSHService.registrationID() ?? ""
    }

    @objc public func setAlias(_ alias: String, completion: @escaping (Bool, String) -> Void) {
        JPUSHService.setAlias(alias, completion: { result, iResCode, seq in
            let success = iResCode == 0
            let message = success ? "Alias set successfully" : "Failed to set alias: \(iResCode)"
            completion(success, message)
        }, seq: Int(Date().timeIntervalSince1970))
    }

    @objc public func deleteAlias(completion: @escaping (Bool, String) -> Void) {
        JPUSHService.deleteAlias({ result, iResCode, seq in
            let success = iResCode == 0
            let message = success ? "Alias deleted successfully" : "Failed to delete alias: \(iResCode)"
            completion(success, message)
        }, seq: Int(Date().timeIntervalSince1970))
    }

    @objc public func addTags(_ tags: Set<String>, completion: @escaping (Bool, String) -> Void) {
        JPUSHService.addTags(tags, completion: { result, iResCode, seq in
            let success = iResCode == 0
            let message = success ? "Tags added successfully" : "Failed to add tags: \(iResCode)"
            completion(success, message)
        }, seq: Int(Date().timeIntervalSince1970))
    }

    @objc public func deleteTags(_ tags: Set<String>, completion: @escaping (Bool, String) -> Void) {
        JPUSHService.deleteTags(tags, completion: { result, iResCode, seq in
            let success = iResCode == 0
            let message = success ? "Tags deleted successfully" : "Failed to delete tags: \(iResCode)"
            completion(success, message)
        }, seq: Int(Date().timeIntervalSince1970))
    }

    @objc public func cleanTags(completion: @escaping (Bool, String) -> Void) {
        JPUSHService.cleanTags({ result, iResCode, seq in
            let success = iResCode == 0
            let message = success ? "Tags cleaned successfully" : "Failed to clean tags: \(iResCode)"
            completion(success, message)
        }, seq: Int(Date().timeIntervalSince1970))
    }

    @objc public func setBadge(_ badge: Int, completion: @escaping (Bool, String) -> Void) {
        DispatchQueue.main.async {
            UIApplication.shared.applicationIconBadgeNumber = badge
            JPUSHService.setBadge(badge)
            completion(true, "Badge set successfully")
        }
    }
}
